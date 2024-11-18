package com.yoajung.jobplanner.roadmap.service.impl;

import com.yoajung.jobplanner.openai.domain.OpenAIRequest;
import com.yoajung.jobplanner.openai.domain.OpenAIResponse;
import com.yoajung.jobplanner.openai.domain.OpenAIResponse.Choice;
import com.yoajung.jobplanner.openai.service.impl.OpenAIRequestServiceImpl;
import com.yoajung.jobplanner.roadmap.dto.response.LectureSuggestion;
import com.yoajung.jobplanner.roadmap.dto.response.WholeRoadMapResponseDTO;
import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMap;
import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMapResponseDTO;
import com.yoajung.jobplanner.roadmap.service.RoadMapService;
import com.yoajung.jobplanner.roadmap.service.generator.OpenAIRequestGenerator;
import com.yoajung.jobplanner.roadmap.service.parser.OnlineLectureParser;
import com.yoajung.jobplanner.roadmap.service.parser.YearRoadMapParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RoadMapServiceImpl implements RoadMapService {
    private final OpenAIRequestServiceImpl openAIRequestServiceImpl;

    @Override
    public Mono<WholeRoadMapResponseDTO> requestWholeRoadMap(String job) {
        OpenAIRequest wholeRoadMapRequest = createWholeRoadMapRequest(job);
        Mono<OpenAIResponse> openAIResponseMono = openAIRequestServiceImpl.sendRequest(wholeRoadMapRequest);
        return openAIResponseMono.map(response -> {
            Choice choice = response.getChoices().getFirst();
            return new WholeRoadMapResponseDTO(choice.getMessage().getContent());
        });
    }

    private OpenAIRequest createWholeRoadMapRequest(String job) {
        String userRequest = """
                Write a Graphviz code describing %s.
                The technologies included in the roadmap should be names like 'programming language' or 'database'.
                """;
        return OpenAIRequestGenerator.generate(String.format(userRequest, job), "gpt/roadmap-graphviz.txt");
    }

    @Override
    public Mono<YearRoadMapResponseDTO> requestRoadMap(String job) {
        OpenAIRequest yearlyRoadMap = createYearRoadMapRequest(job);
        Mono<OpenAIResponse> openAIResponseMono = openAIRequestServiceImpl.sendRequest(yearlyRoadMap);
        return openAIResponseMono.flatMap(response -> {
            Choice choice = response.getChoices().getFirst();
            String content = choice.getMessage().getContent();
            YearRoadMap yearRoadMap = YearRoadMapParser.parse(content);
            return requestLectureSuggestion(content, job).map(
                    lectureSuggestion -> new YearRoadMapResponseDTO(yearRoadMap, lectureSuggestion));
        });
    }

    private Mono<LectureSuggestion> requestLectureSuggestion(String prevResponse, String job) {
        OpenAIRequest onlineLectureRequest = createLectureRequest(prevResponse, job);
        Mono<OpenAIResponse> openAIResponseMono = openAIRequestServiceImpl.sendRequest(onlineLectureRequest);
        return openAIResponseMono.map(response -> {
            Choice choice = response.getChoices().getFirst();
            String content = choice.getMessage().getContent();
            return OnlineLectureParser.parse(content);
        });
    }

    private OpenAIRequest createYearRoadMapRequest(String job) {
        String userRequest = """
                Create a 1-year roadmap for becoming a %s.
                Each period is divided by $ (one anchor) for months,
                # (one hash Symbol) for section titles,
                and * (one asterisk) for items to study or learn. Ensure the response follows this format strictly.
                """;

        return OpenAIRequestGenerator.generate(String.format(userRequest, job), "gpt/year-roadmap.txt");
    }

    private OpenAIRequest createLectureRequest(String prevResponse, String job) {
        String userRequest = """
                %s is your previous response outlining a one-year roadmap for becoming a %s.
                Recommend 10 online courses that thoroughly cover all the sections mentioned in this roadmap.
                Format your recommendations as: title & link | section.
                Only include courses from Udemy, Coursera, or Inflearn, and ensure all links provided are active and accessible.
                """;
        return OpenAIRequestGenerator.generate(String.format(userRequest, prevResponse, job), "gpt/online-lecture.txt");
    }
}

package com.yoajung.jobplanner.roadmap.service.impl;

import com.yoajung.jobplanner.openai.domain.OpenAIRequest;
import com.yoajung.jobplanner.openai.domain.OpenAIResponse.Choice;
import com.yoajung.jobplanner.openai.service.OpenAIRequestService;
import com.yoajung.jobplanner.roadmap.dto.response.OutlookResponseDTO;
import com.yoajung.jobplanner.roadmap.service.generator.OpenAIRequestGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OutlookServiceImpl {
    private final OpenAIRequestService openAIRequestService;

    public Mono<OutlookResponseDTO> requestOutlookRequest(String job) {
        OpenAIRequest outlookRequest = createOutlookRequest(job);
        return openAIRequestService.sendRequest(outlookRequest).map(response -> {
            Choice choice = response.getChoices().getFirst();
            String content = choice.getMessage().getContent();
            return new OutlookResponseDTO(content);
        });
    }

    private OpenAIRequest createOutlookRequest(String job) {
        String userRequest = """
                Write a detailed outlook on %s in Markdown format and make it visually appealing.
                The content should include the following sections: sustainability, future development direction, career progression,
                 salary and work-life balance, and conclusion.
                 For the salary and work-life balance section, ensure to include sources based on Korean standards.
                """;
        return OpenAIRequestGenerator.generate(String.format(userRequest, job), "gpt/outlook.txt");
    }
}

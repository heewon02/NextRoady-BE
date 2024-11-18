package com.yoajung.jobplanner.roadmap.service.impl;

import com.yoajung.jobplanner.openai.domain.OpenAIRequest;
import com.yoajung.jobplanner.openai.domain.OpenAIResponse.Choice;
import com.yoajung.jobplanner.openai.service.OpenAIRequestService;
import com.yoajung.jobplanner.roadmap.dto.response.JobValidationResponseDTO;
import com.yoajung.jobplanner.roadmap.service.JobValidationService;
import com.yoajung.jobplanner.roadmap.service.generator.OpenAIRequestGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JobValidationServiceImpl implements JobValidationService {
    private final OpenAIRequestService openAIRequestService;

    @Override
    public Mono<JobValidationResponseDTO> validateJob(String job) {
        OpenAIRequest validationRequest = createValidationRequest(job);
        return openAIRequestService.sendRequest(validationRequest).map(response -> {
            Choice choice = response.choices().getFirst();
            String content = choice.message().content();
            Boolean isJobExisted = parseResponse(content);
            return new JobValidationResponseDTO(isJobExisted);
        });
    }

    private OpenAIRequest createValidationRequest(String job) {
        String userRequest = """
                Respond whether the job %s exists. It must be human profession. The response must be either 'yes' or 'no' only.
                """;
        return OpenAIRequestGenerator.generate(String.format(userRequest, job), "gpt/job-validation.txt");
    }

    private Boolean parseResponse(String response) {
        if (!(response.equals("yes") || response.equals("no"))) {
            throw new RuntimeException("응답이 yes 또는 no가 아닙니다.");
        }
        return response.equals("yes");
    }
}

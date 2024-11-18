package com.yoajung.jobplanner.openai.service.impl;

import com.yoajung.jobplanner.openai.domain.OpenAIRequest;
import com.yoajung.jobplanner.openai.domain.OpenAIResponse;
import com.yoajung.jobplanner.openai.service.OpenAIRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenAIRequestServiceImpl implements OpenAIRequestService {

    private final WebClient webClient;

    public Mono<OpenAIResponse> sendRequest(OpenAIRequest request) {
        String defaultSetting = "Do not add any characters other than the format I provided in the response.";
        request.addSystemSetting(defaultSetting);
        return webClient
                .post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        status -> !status.is2xxSuccessful(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("GPT API 요청 실패: " + errorBody)))
                )
                .bodyToMono(OpenAIResponse.class);
    }
}

package com.yoajung.jobplanner.openai.service;

import com.yoajung.jobplanner.openai.domain.OpenAIRequest;
import com.yoajung.jobplanner.openai.domain.OpenAIResponse;
import reactor.core.publisher.Mono;

public interface OpenAIRequestService {
    Mono<OpenAIResponse> sendRequest(OpenAIRequest request);
}

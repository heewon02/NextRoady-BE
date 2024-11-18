package com.yoajung.jobplanner.openai.domain;

import java.util.List;

public record OpenAIResponse(String id, String object, long created, String model,
                             com.yoajung.jobplanner.openai.domain.OpenAIResponse.Usage usage, List<Choice> choices) {
    public record Choice(int index, Message message, String finish_reason) {
    }

    public record Message(String role, String content) {
    }

    public record Usage(int promptTokens, int completionTokens, int totalTokens) {
    }
}

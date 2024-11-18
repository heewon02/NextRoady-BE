package com.yoajung.jobplanner.openai.domain;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpenAIResponse {
    private final String id;
    private final String object;
    private final long created;
    private final String model;
    private final Usage usage;
    private final List<Choice> choices;

    @Getter
    public static class Choice {
        private int index;
        private Message message;
        private String finish_reason;
    }

    @Getter
    public static class Message {
        private String role;
        private String content;
    }

    @Getter
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
    }
}

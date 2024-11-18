package com.yoajung.jobplanner.openai.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class OpenAIRequest {
    private final String model;
    private final List<Message> messages;

    private OpenAIRequest(String model) {
        this.model = model;
        messages = new ArrayList<>();
    }

    public static OpenAIRequest create4oMiniRequest() {
        return new OpenAIRequest("gpt-4o-mini");
    }

    public void addUserRequest(String content) {
        Message userMessage = Message.createUserMessage(content);
        messages.add(userMessage);
    }

    public void addSystemSetting(String content) {
        Message systemMessage = Message.createSystemMessage(content);
        messages.add(systemMessage);
    }

    private record Message(String role, String content) {

        public static Message createUserMessage(String content) {
            return new Message("user", content);
        }

        public static Message createSystemMessage(String content) {
            return new Message("system", content);
        }
    }
}

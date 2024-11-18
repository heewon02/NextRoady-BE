package com.yoajung.jobplanner.openai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${gpt-secret}")
    private String openApiKey;

    @Bean
    public WebClient openAIWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + openApiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}

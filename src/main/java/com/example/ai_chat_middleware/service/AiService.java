package com.example.ai_chat_middleware.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    private final RestClient restClient;
    private final String model;

    public AiService(
            @Value("${openrouter.api.key}") String apiKey,
            @Value("${openrouter.api.url}") String apiUrl,
            @Value("${openrouter.model}") String model
    ){
        this.model = model;
        this.restClient = RestClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-type", "application/json")

                .build();
    }

    public String sendMessage(String systemPrompt, String userMessage){
        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userMessage)
                )
        );
        Map response = restClient.post()
                .body(body)
                .retrieve()
                .body(Map.class);

        List<Map> choices = (List<Map>) response.get("choices");
        Map message = (Map) choices.get(0).get("message");
        return (String) message.get("content");

    }

}

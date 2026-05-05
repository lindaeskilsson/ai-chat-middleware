package com.example.ai_chat_middleware.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PersonalityService {

    private static final Map<String, String> PERSONALITIES = Map.of(
            "helper", "You are a friendly and helpful assistant. Answer clearly and kindly.",
            "pirate", "You are a pirate. Speak like a pirate in every response. Arrr!",
            "coder", "You are an expert software engineer. Answer all questions with clean code examples and technical precision."
    );

    public String getSystemPrompt(String personality) {
        return PERSONALITIES.getOrDefault(personality, PERSONALITIES.get("helper"));
    }

}



package com.example.ai_chat_middleware.model;

public record ChatRequest (
    String personality,
    String message,
    String sessionId
) {}

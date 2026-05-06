package com.example.ai_chat_middleware.controller;


import com.example.ai_chat_middleware.model.ChatRequest;
import com.example.ai_chat_middleware.model.ChatResponse;
import com.example.ai_chat_middleware.model.Message;
import com.example.ai_chat_middleware.service.AiService;
import com.example.ai_chat_middleware.service.ChatMemoryService;
import com.example.ai_chat_middleware.service.PersonalityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ChatController {

    private final PersonalityService personalityService;
    private final AiService aiService;
    private final ChatMemoryService chatMemoryService;

    public ChatController(PersonalityService personalityService, AiService aiService, ChatMemoryService chatMemoryService) {
        this.personalityService = personalityService;
        this.aiService = aiService;
        this.chatMemoryService = chatMemoryService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String systemPrompt = personalityService.getSystemPrompt(request.personality());

        List<Message> history = request.sessionId() != null
                ? chatMemoryService.getHistory(request.sessionId())
                : new ArrayList<>();

        String reply = aiService.sendMessage(systemPrompt, request.message(), history);

        if (request.sessionId() != null) {
            chatMemoryService.addMessage(request.sessionId(), new Message("user", request.message()));
            chatMemoryService.addMessage(request.sessionId(), new Message("assistant", reply));
        }

        return new ChatResponse(reply);
    }
}

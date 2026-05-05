package com.example.ai_chat_middleware.controller;


import com.example.ai_chat_middleware.model.ChatRequest;
import com.example.ai_chat_middleware.model.ChatResponse;
import com.example.ai_chat_middleware.service.AiService;
import com.example.ai_chat_middleware.service.PersonalityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    private final PersonalityService personalityService;
    private final AiService aiService;

    public ChatController(PersonalityService personalityService, AiService aiService) {
        this.personalityService = personalityService;
        this.aiService = aiService;
    }

    @PostMapping ("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request){
        String systemPrompt = personalityService.getSystemPrompt(request.personality());
        String reply = aiService.sendMessage(systemPrompt, request.message());
        System.out.println("System prompt: " + systemPrompt);
        return new ChatResponse(reply);
    }

}

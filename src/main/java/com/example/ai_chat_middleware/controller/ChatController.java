package com.example.ai_chat_middleware.controller;


import com.example.ai_chat_middleware.model.ChatRequest;
import com.example.ai_chat_middleware.model.ChatResponse;
import com.example.ai_chat_middleware.service.PersonalityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    private final PersonalityService personalityService;

    public ChatController(PersonalityService personalityService) {
        this.personalityService = personalityService;
    }

    @PostMapping ("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request){
        String systemPrompt = personalityService.getSystemPrompt(request.personality());
        System.out.println("System prompt: " + systemPrompt);
        return new ChatResponse("Svaret kommer snart.");
    }

}

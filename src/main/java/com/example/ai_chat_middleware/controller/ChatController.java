package com.example.ai_chat_middleware.controller;


import com.example.ai_chat_middleware.model.ChatRequest;
import com.example.ai_chat_middleware.model.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @PostMapping ("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request){
        return new ChatResponse("Svaret kommer snart.");
    }

}

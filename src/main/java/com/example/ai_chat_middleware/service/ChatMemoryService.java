package com.example.ai_chat_middleware.service;

import com.example.ai_chat_middleware.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatMemoryService {

    private final Map<String, List<Message>> sessions = new HashMap<>();

    public List<Message> getHistory(String sessionId) {
        return sessions.getOrDefault(sessionId, new ArrayList<>());
    }

    public void addMessage(String sessionId, Message message) {
        sessions.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(message);
    }
}

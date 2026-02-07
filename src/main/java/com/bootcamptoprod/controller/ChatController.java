package com.bootcamptoprod.controller;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @Autowired
    private ChatModel chatModel;

    @PostMapping("/chat")
    public ResponseEntity<Map<String, Object>> simpleChat(@RequestBody Map<String, String> request) {

        // Extract user message from the incoming request
        String userMessage = request.get("message");

        // Sends prompt to the Docker-hosted model and gets the string response
        String response = chatModel.chat(userMessage);

        // Wrap response in a simple JSON structure
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("aiResponse", response);

        return ResponseEntity.ok(result);
    }
}
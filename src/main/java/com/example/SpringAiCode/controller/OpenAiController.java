package com.example.SpringAiCode.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

    private  final OpenAiChatModel chatModel;
    private final ChatClient chatClient;

    public OpenAiController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/api/chatModel/{message}")
    public String getOpenAiResponseUsingChatModel(@PathVariable String message) {
       return chatModel.call(message);
    }
    @GetMapping("/api/chatClient/{message}")
    public ResponseEntity<String> getOpenAiResponse(@PathVariable String message) {
        return  ResponseEntity.ok(chatClient.prompt(message)
                .call()
                .content());

    }
}

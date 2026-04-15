package com.zosh.controller;

import com.zosh.dto.PromptBody;
import com.zosh.response.ApiResponse;
import com.zosh.service.ChatbotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/chat")
public class ChatbotController {


    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping
 public ResponseEntity<ApiResponse> getCoinDetails(@RequestBody PromptBody prompt) throws Exception {
       chatbotService.getCoinDetails(prompt.getPrompt());


     ApiResponse response = new ApiResponse();
     response.setMessage(prompt.getPrompt());
     return new  ResponseEntity<>(response, HttpStatus.OK);
 }

}

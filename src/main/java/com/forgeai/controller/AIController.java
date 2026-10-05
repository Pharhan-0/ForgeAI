package com.forgeai.controller;

import com.forgeai.dto.AIRequest;
import com.forgeai.dto.PromptDebugResponse;
import com.forgeai.service.AIService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public Map<String, String> generate(
            @RequestBody AIRequest request
    ) {

        String response =
                aiService.generateResponse(
                        request.getConversationId(),
                        request.getPrompt()
                );

        return Map.of(
                "response",
                response
        );
    }

    @PostMapping("/debug-prompt")
    public PromptDebugResponse debugPrompt(
            @RequestBody AIRequest request
    ) {

        return aiService.debugPrompt(
                request.getConversationId(),
                request.getPrompt()
        );
    }

    @DeleteMapping("/conversation/{conversationId}")
    public Map<String, String> clearConversation(
            @PathVariable String conversationId
    ) {

        aiService.clearConversation(
                conversationId
        );

        return Map.of(
                "status",
                "Conversation cleared successfully."
        );
    }

    @GetMapping("/conversation/{conversationId}/messages")
    public Map<String, Object> getMessageCount(
            @PathVariable String conversationId
    ) {

        int count =
                aiService.getMessageCount(
                        conversationId
                );

        return Map.of(
                "conversationId",
                conversationId,
                "messageCount",
                count
        );
    }
}
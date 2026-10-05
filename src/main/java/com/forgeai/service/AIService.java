package com.forgeai.service;

import com.forgeai.ai.AIProvider;
import com.forgeai.dto.PromptDebugResponse;
import com.forgeai.memory.MemoryManager;
import com.forgeai.prompt.PromptBuilder;
import com.forgeai.prompt.PromptContext;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final AIProvider aiProvider;
    private final PromptBuilder promptBuilder;
    private final MemoryManager memoryManager;

    public AIService(
            AIProvider aiProvider,
            PromptBuilder promptBuilder,
            MemoryManager memoryManager
    ) {
        this.aiProvider = aiProvider;
        this.promptBuilder = promptBuilder;
        this.memoryManager = memoryManager;
    }

    public String generateResponse(
            String conversationId,
            String prompt
    ) {

        validateRequest(conversationId, prompt);

        PromptContext context =
                promptBuilder.createContext(
                        conversationId,
                        prompt
                );

        String finalPrompt =
                promptBuilder.build(context);

        String response =
                aiProvider.generate(finalPrompt);

        memoryManager.addMessage(
                conversationId,
                "user",
                prompt
        );

        memoryManager.addMessage(
                conversationId,
                "assistant",
                response
        );

        return response;
    }

    public PromptDebugResponse debugPrompt(
            String conversationId,
            String prompt
    ) {

        validateRequest(conversationId, prompt);

        PromptContext context =
                promptBuilder.createContext(
                        conversationId,
                        prompt
                );

        String finalPrompt =
                promptBuilder.build(context);

        return new PromptDebugResponse(
                context.getSystemInstructions(),
                context.getPolicyInstructions(),
                context.getPersonality(),
                context.getMemory(),
                context.getRagContext(),
                context.getToolInformation(),
                context.getConversationHistory(),
                context.getUserMessage(),
                finalPrompt
        );
    }

    public void clearConversation(
            String conversationId
    ) {

        if (conversationId == null ||
                conversationId.isBlank()) {

            throw new IllegalArgumentException(
                    "Conversation ID cannot be empty."
            );
        }

        memoryManager.clearConversation(
                conversationId
        );
    }

    public int getMessageCount(
            String conversationId
    ) {

        if (conversationId == null ||
                conversationId.isBlank()) {

            throw new IllegalArgumentException(
                    "Conversation ID cannot be empty."
            );
        }

        return memoryManager.getMessageCount(
                conversationId
        );
    }

    private void validateRequest(
            String conversationId,
            String prompt
    ) {

        if (conversationId == null ||
                conversationId.isBlank()) {

            throw new IllegalArgumentException(
                    "Conversation ID cannot be empty."
            );
        }

        if (prompt == null ||
                prompt.isBlank()) {

            throw new IllegalArgumentException(
                    "Prompt cannot be empty."
            );
        }
    }
}
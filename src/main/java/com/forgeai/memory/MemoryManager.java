package com.forgeai.memory;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryManager {

    private final Map<String, ConversationMemory> conversations =
            new ConcurrentHashMap<>();

    public ConversationMemory getOrCreateConversation(
            String conversationId
    ) {

        if (conversationId == null || conversationId.isBlank()) {
            throw new IllegalArgumentException(
                    "Conversation ID cannot be empty."
            );
        }

        return conversations.computeIfAbsent(
                conversationId,
                ConversationMemory::new
        );
    }

    public void addMessage(
            String conversationId,
            String role,
            String content
    ) {

        ConversationMemory conversation =
                getOrCreateConversation(conversationId);

        conversation.addMessage(role, content);
    }

    public String buildConversationHistory(
            String conversationId
    ) {

        ConversationMemory conversation =
                getOrCreateConversation(conversationId);

        StringBuilder history = new StringBuilder();

        for (MemoryMessage message : conversation.getMessages()) {

            history.append(
                    message.getRole().toUpperCase()
            );

            history.append(": ");

            history.append(
                    message.getContent()
            );

            history.append("\n");
        }

        return history.toString();
    }

    public void clearConversation(
            String conversationId
    ) {

        ConversationMemory conversation =
                conversations.get(conversationId);

        if (conversation != null) {
            conversation.clear();
        }
    }

    public int getMessageCount(
            String conversationId
    ) {

        ConversationMemory conversation =
                conversations.get(conversationId);

        if (conversation == null) {
            return 0;
        }

        return conversation.getMessages().size();
    }
}
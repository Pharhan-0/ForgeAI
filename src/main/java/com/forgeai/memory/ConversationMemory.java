package com.forgeai.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversationMemory {

    private final String conversationId;

    private final List<MemoryMessage> messages =
            new ArrayList<>();

    public ConversationMemory(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void addMessage(String role, String content) {

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException(
                    "Message role cannot be empty."
            );
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException(
                    "Message content cannot be empty."
            );
        }

        messages.add(
                new MemoryMessage(role, content)
        );
    }

    public List<MemoryMessage> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void clear() {
        messages.clear();
    }
}
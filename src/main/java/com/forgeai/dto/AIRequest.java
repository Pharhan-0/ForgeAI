package com.forgeai.dto;

public class AIRequest {

    private String conversationId;
    private String prompt;

    public AIRequest() {
    }

    public AIRequest(
            String conversationId,
            String prompt
    ) {
        this.conversationId = conversationId;
        this.prompt = prompt;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
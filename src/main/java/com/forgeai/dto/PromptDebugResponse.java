package com.forgeai.dto;

public class PromptDebugResponse {

    private String systemInstructions;
    private String policyInstructions;
    private String personality;
    private String memory;
    private String ragContext;
    private String toolInformation;
    private String conversationHistory;
    private String userMessage;
    private String finalPrompt;

    public PromptDebugResponse() {
    }

    public PromptDebugResponse(
            String systemInstructions,
            String policyInstructions,
            String personality,
            String memory,
            String ragContext,
            String toolInformation,
            String conversationHistory,
            String userMessage,
            String finalPrompt
    ) {
        this.systemInstructions = systemInstructions;
        this.policyInstructions = policyInstructions;
        this.personality = personality;
        this.memory = memory;
        this.ragContext = ragContext;
        this.toolInformation = toolInformation;
        this.conversationHistory = conversationHistory;
        this.userMessage = userMessage;
        this.finalPrompt = finalPrompt;
    }

    public String getSystemInstructions() {
        return systemInstructions;
    }

    public String getPolicyInstructions() {
        return policyInstructions;
    }

    public String getPersonality() {
        return personality;
    }

    public String getMemory() {
        return memory;
    }

    public String getRagContext() {
        return ragContext;
    }

    public String getToolInformation() {
        return toolInformation;
    }

    public String getConversationHistory() {
        return conversationHistory;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getFinalPrompt() {
        return finalPrompt;
    }
}
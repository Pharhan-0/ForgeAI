package com.forgeai.prompt;

public class PromptContext {

    private String systemInstructions;
    private String policyInstructions;
    private String personality;
    private String memory;
    private String ragContext;
    private String toolInformation;
    private String conversationHistory;
    private String userMessage;

    public PromptContext() {
    }

    public String getSystemInstructions() {
        return systemInstructions;
    }

    public void setSystemInstructions(String systemInstructions) {
        this.systemInstructions = systemInstructions;
    }

    public String getPolicyInstructions() {
        return policyInstructions;
    }

    public void setPolicyInstructions(String policyInstructions) {
        this.policyInstructions = policyInstructions;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getRagContext() {
        return ragContext;
    }

    public void setRagContext(String ragContext) {
        this.ragContext = ragContext;
    }

    public String getToolInformation() {
        return toolInformation;
    }

    public void setToolInformation(String toolInformation) {
        this.toolInformation = toolInformation;
    }

    public String getConversationHistory() {
        return conversationHistory;
    }

    public void setConversationHistory(String conversationHistory) {
        this.conversationHistory = conversationHistory;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
package com.forgeai.prompt;

import com.forgeai.memory.MemoryManager;
import com.forgeai.personality.PersonalityManager;
import com.forgeai.policy.PolicyManager;
import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    private final PolicyManager policyManager;
    private final PersonalityManager personalityManager;
    private final MemoryManager memoryManager;

    public PromptBuilder(
            PolicyManager policyManager,
            PersonalityManager personalityManager,
            MemoryManager memoryManager
    ) {
        this.policyManager = policyManager;
        this.personalityManager = personalityManager;
        this.memoryManager = memoryManager;
    }

    public PromptContext createContext(
            String conversationId,
            String userPrompt
    ) {

        PromptContext context = new PromptContext();

        context.setSystemInstructions(
                "You are the AI assistant operating inside ForgeAI."
        );

        context.setPolicyInstructions(
                policyManager.buildPolicyInstructions()
        );

        context.setPersonality(
                personalityManager.buildPersonalityInstructions()
        );

        context.setMemory("");

        context.setRagContext("");

        context.setToolInformation("");

        context.setConversationHistory(
                memoryManager.buildConversationHistory(
                        conversationId
                )
        );

        context.setUserMessage(userPrompt);

        return context;
    }

    public String build(PromptContext context) {

        StringBuilder prompt = new StringBuilder();

        appendSection(
                prompt,
                "SYSTEM INSTRUCTIONS",
                context.getSystemInstructions()
        );

        appendSection(
                prompt,
                "POLICY",
                context.getPolicyInstructions()
        );

        appendSection(
                prompt,
                "PERSONALITY",
                context.getPersonality()
        );

        appendSection(
                prompt,
                "MEMORY",
                context.getMemory()
        );

        appendSection(
                prompt,
                "RETRIEVED CONTEXT",
                context.getRagContext()
        );

        appendSection(
                prompt,
                "AVAILABLE TOOLS",
                context.getToolInformation()
        );

        appendSection(
                prompt,
                "CONVERSATION HISTORY",
                context.getConversationHistory()
        );

        appendSection(
                prompt,
                "USER",
                context.getUserMessage()
        );

        return prompt.toString();
    }

    private void appendSection(
            StringBuilder prompt,
            String title,
            String content
    ) {

        if (content == null || content.isBlank()) {
            return;
        }

        prompt.append("\n=== ")
                .append(title)
                .append(" ===\n");

        prompt.append(content.trim())
                .append("\n");
    }
}
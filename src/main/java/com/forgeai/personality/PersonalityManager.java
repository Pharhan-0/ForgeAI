package com.forgeai.personality;

import org.springframework.stereotype.Component;

@Component
public class PersonalityManager {

    private PersonalityConfiguration configuration;

    public PersonalityManager() {

        this.configuration = new PersonalityConfiguration(
                true,
                PersonalityMode.DEFAULT,
                ""
        );
    }

    public PersonalityConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(PersonalityConfiguration configuration) {

        if (configuration == null) {
            throw new IllegalArgumentException(
                    "Personality configuration cannot be null."
            );
        }

        if (configuration.getMode() == null) {
            configuration.setMode(PersonalityMode.DEFAULT);
        }

        this.configuration = configuration;
    }

    public String buildPersonalityInstructions() {

        if (!configuration.isEnabled()) {
            return "";
        }

        PersonalityMode mode = configuration.getMode();

        if (mode == null) {
            mode = PersonalityMode.DEFAULT;
        }

        return switch (mode) {

            case DEFAULT ->
                    """
                    Respond naturally and clearly.
                    Maintain a helpful and professional communication style.
                    """;

            case PROFESSIONAL ->
                    """
                    Communicate in a professional and structured manner.
                    Use precise language and organize information clearly.
                    Avoid unnecessary casual language.
                    """;

            case TUTOR ->
                    """
                    Act as a patient university tutor.
                    Explain concepts using simple language.
                    Break difficult concepts into understandable steps.
                    Include a short example when useful.
                    """;

            case CONCISE ->
                    """
                    Be concise and direct.
                    Provide the essential information without unnecessary explanation.
                    Avoid excessive repetition.
                    """;

            case FRIENDLY ->
                    """
                    Communicate in a friendly, approachable, and encouraging manner.
                    Keep explanations easy to understand while remaining accurate.
                    """;

            case CUSTOM ->
                    configuration.getCustomInstructions() == null
                            ? ""
                            : configuration.getCustomInstructions();
        };
    }
}
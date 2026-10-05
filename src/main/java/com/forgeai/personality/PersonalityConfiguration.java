package com.forgeai.personality;

public class PersonalityConfiguration {

    private boolean enabled;
    private PersonalityMode mode;
    private String customInstructions;

    public PersonalityConfiguration() {
    }

    public PersonalityConfiguration(
            boolean enabled,
            PersonalityMode mode,
            String customInstructions
    ) {
        this.enabled = enabled;
        this.mode = mode;
        this.customInstructions = customInstructions;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public PersonalityMode getMode() {
        return mode;
    }

    public void setMode(PersonalityMode mode) {
        this.mode = mode;
    }

    public String getCustomInstructions() {
        return customInstructions;
    }

    public void setCustomInstructions(String customInstructions) {
        this.customInstructions = customInstructions;
    }
}
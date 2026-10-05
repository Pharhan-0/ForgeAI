package com.forgeai.policy;

public class PolicyConfiguration {

    private boolean enabled;
    private PolicyMode mode;
    private int strictness;
    private String customInstructions;

    public PolicyConfiguration() {
    }

    public PolicyConfiguration(
            boolean enabled,
            PolicyMode mode,
            int strictness,
            String customInstructions
    ) {
        this.enabled = enabled;
        this.mode = mode;
        this.strictness = strictness;
        this.customInstructions = customInstructions;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public PolicyMode getMode() {
        return mode;
    }

    public void setMode(PolicyMode mode) {
        this.mode = mode;
    }

    public int getStrictness() {
        return strictness;
    }

    public void setStrictness(int strictness) {
        this.strictness = strictness;
    }

    public String getCustomInstructions() {
        return customInstructions;
    }

    public void setCustomInstructions(String customInstructions) {
        this.customInstructions = customInstructions;
    }
}
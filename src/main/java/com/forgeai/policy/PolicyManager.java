package com.forgeai.policy;

import org.springframework.stereotype.Component;

@Component
public class PolicyManager {

    private PolicyConfiguration configuration;

    public PolicyManager() {
        this.configuration = new PolicyConfiguration(
                true,
                PolicyMode.STANDARD,
                5,
                ""
        );
    }

    public PolicyConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(PolicyConfiguration configuration) {

        if (configuration == null) {
            throw new IllegalArgumentException(
                    "Policy configuration cannot be null."
            );
        }

        configuration.setStrictness(
                normalizeStrictness(configuration.getStrictness())
        );

        if (configuration.getMode() == null) {
            configuration.setMode(PolicyMode.STANDARD);
        }

        if (configuration.getCustomInstructions() == null) {
            configuration.setCustomInstructions("");
        }

        this.configuration = configuration;
    }

    public String buildPolicyInstructions() {

        if (!configuration.isEnabled()) {
            return "";
        }

        return switch (configuration.getMode()) {

            case STANDARD -> buildStandardPolicy();

            case CUSTOM -> buildCustomPolicy();

            case EXPERIMENTAL -> buildExperimentalPolicy();
        };
    }

    private String buildStandardPolicy() {

        String strictnessInstructions =
                getStrictnessInstructions(
                        configuration.getStrictness()
                );

        return """
                You are the AI assistant operating inside ForgeAI.
                Provide helpful, accurate, and clear responses.
                Follow the application's configured policies.

                APPLICATION POLICY STRICTNESS: %d/10

                %s
                """.formatted(
                configuration.getStrictness(),
                strictnessInstructions
        );
    }

    private String buildCustomPolicy() {

        String customInstructions =
                configuration.getCustomInstructions();

        if (customInstructions == null ||
                customInstructions.isBlank()) {

            return """
                    ForgeAI is operating in CUSTOM policy mode,
                    but no custom instructions have been configured.

                    Provide helpful, accurate, and clear responses.
                    """;
        }

        return """
                ForgeAI is operating in CUSTOM policy mode.

                APPLICATION POLICY STRICTNESS: %d/10

                CUSTOM INSTRUCTIONS:
                %s
                """.formatted(
                configuration.getStrictness(),
                customInstructions.trim()
        );
    }

    private String buildExperimentalPolicy() {

        return """
                ForgeAI is operating in EXPERIMENTAL policy mode.

                Application-level policy intervention is minimized.

                Respond according to the user's request while remaining
                within the capabilities and behavior of the underlying model.

                IMPORTANT:
                This configuration does not remove or override safeguards,
                behavioral constraints, or policies that may exist inside
                the underlying model or its provider.
                """;
    }

    private String getStrictnessInstructions(int strictness) {

        if (strictness <= 3) {

            return """
                    Apply a low level of application-level policy intervention.
                    Prioritize following the user's instructions.
                    """;
        }

        if (strictness <= 7) {

            return """
                    Apply a moderate level of application-level policy intervention.
                    Prioritize helpful, accurate, and responsible responses.
                    Follow the application's configured behavior.
                    """;
        }

        return """
                Apply a high level of application-level policy intervention.
                Carefully follow the application's configured behavior and
                avoid providing responses that conflict with those policies.
                """;
    }

    private int normalizeStrictness(int strictness) {

        if (strictness < 0) {
            return 0;
        }

        if (strictness > 10) {
            return 10;
        }

        return strictness;
    }
}
package com.forgeai.ai;

import com.forgeai.config.AIModelProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class OllamaProvider implements AIProvider {

    private final RestClient restClient;
    private final AIModelProperties properties;

    public OllamaProvider(AIModelProperties properties) {

        this.properties = properties;

        this.restClient = RestClient.builder()
                .baseUrl(properties.getOllamaUrl())
                .build();
    }

    @Override
    public String generate(String prompt) {

        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("Prompt cannot be empty.");
        }

        Map<String, Object> request = Map.of(
                "model", properties.getModel(),
                "prompt", prompt,
                "stream", properties.isStream()
        );

        Map<?, ?> response = restClient.post()
                .uri("/api/generate")
                .body(request)
                .retrieve()
                .body(Map.class);

        if (response == null || response.get("response") == null) {
            throw new IllegalStateException(
                    "Ollama returned an empty response."
            );
        }

        return response.get("response").toString();
    }
}
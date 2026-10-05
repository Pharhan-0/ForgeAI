package com.forgeai.controller;

import com.forgeai.personality.PersonalityConfiguration;
import com.forgeai.personality.PersonalityManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personality")
public class PersonalityController {

    private final PersonalityManager personalityManager;

    public PersonalityController(PersonalityManager personalityManager) {
        this.personalityManager = personalityManager;
    }

    @GetMapping
    public PersonalityConfiguration getPersonality() {
        return personalityManager.getConfiguration();
    }

    @PostMapping("/configure")
    public PersonalityConfiguration configurePersonality(
            @RequestBody PersonalityConfiguration configuration
    ) {
        personalityManager.setConfiguration(configuration);

        return personalityManager.getConfiguration();
    }
}

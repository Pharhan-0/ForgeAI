package com.forgeai.policy.controller;

import com.forgeai.policy.PolicyConfiguration;
import com.forgeai.policy.PolicyManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    private final PolicyManager policyManager;

    public PolicyController(PolicyManager policyManager) {
        this.policyManager = policyManager;
    }

    @GetMapping
    public PolicyConfiguration getPolicy() {
        return policyManager.getConfiguration();
    }

    @PostMapping("/configure")
    public PolicyConfiguration configurePolicy(
            @RequestBody PolicyConfiguration configuration
    ) {
        policyManager.setConfiguration(configuration);
        return policyManager.getConfiguration();
    }
}
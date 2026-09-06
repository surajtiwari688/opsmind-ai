package com.opsmind.api.controller;

import com.opsmind.api.model.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public HealthResponse health() {
        return new HealthResponse(
            "UP",
            "OpsMind AI",
            "1.0.0",
            "development"
        );
    }
}
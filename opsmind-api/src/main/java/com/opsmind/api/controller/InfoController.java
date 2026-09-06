package com.opsmind.api.controller;

import com.opsmind.api.model.ApplicationInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/api/info")
    public ApplicationInfo info() {
        return new ApplicationInfo(
                "OpsMind AI",
                "1.0.0",
                "An AI-powered DevOps Incident & Observability Platform",
                "development",
                "running");
    }
}

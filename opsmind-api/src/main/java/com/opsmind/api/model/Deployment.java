package com.opsmind.api.model;

public record Deployment(
        Long id,
        String application,
        String version,
        String environment,
        String status,
        String deployedBy) {
}

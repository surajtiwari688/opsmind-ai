package com.opsmind.api.model;

public record Incident(
        Long id,
        String title,
        String service,
        String severity,
        String status,
        String description) {
}
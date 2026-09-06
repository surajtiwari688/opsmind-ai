package com.opsmind.api.model;

public record ApplicationInfo(
        String application,
        String version,
        String description,
        String environment,
        String status) {
}

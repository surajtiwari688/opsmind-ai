package com.opsmind.api.model;

public record HealthResponse(
    String status,
    String application,
    String version,
    String environment
) {

}
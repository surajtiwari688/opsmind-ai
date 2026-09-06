package com.opsmind.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record IncidentRequest(
                @NotNull(message = "Incident ID is required") Long id,

                @NotBlank(message = "Incident title is required") String title,

                @NotBlank(message = "Incident service is required") String service,

                @NotBlank(message = "Incident severity is required") @Pattern(regexp = "LOW|MEDIUM|HIGH|CRITICAL", message = "Severity must be one of: LOW, MEDIUM, HIGH, CRITICAL") String severity,

                @NotBlank(message = "Incident status is required") @Pattern(regexp = "OPEN|IN_PROGRESS|RESOLVED|CLOSED", message = "Status must be one of: OPEN, IN_PROGRESS, RESOLVED, CLOSED") String status,

                @NotBlank(message = "Incident description is required") String description) {
}
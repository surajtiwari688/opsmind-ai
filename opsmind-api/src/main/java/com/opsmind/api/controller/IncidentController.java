package com.opsmind.api.controller;

import com.opsmind.api.exception.ResourceNotFoundException;
import com.opsmind.api.model.Incident;
import org.springframework.web.bind.annotation.*;
import com.opsmind.api.dto.IncidentRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final List<Incident> incidents = new ArrayList<>();

    public IncidentController() {
        // Sample incidents for demonstration
        incidents.add(new Incident(1001L, "High API Response Time", "opsmind-api", "HIGH", "OPEN",
                "API response time exceeded 3 seconds"));
        incidents.add(new Incident(1002L, "Pod CrashLoopBackOff", "opsmind-api", "CRITICAL", "OPEN",
                "Application pod is repeatedly restarting"));
    }

    @GetMapping
    public List<Incident> getIncidents() {
        return incidents;
    }

    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidents.stream()
                .filter(incident -> incident.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found with id: " + id));

    }

    @PostMapping
    public Incident createIncident(@Valid @RequestBody IncidentRequest request) {
        Incident incident = new Incident(
                request.id(),
                request.title(),
                request.service(),
                request.severity(),
                request.status(),
                request.description());
        incidents.add(incident);
        return incident;
    }

}

package com.opsmind.api.controller;

import com.opsmind.api.exception.ResourceNotFoundException;
import com.opsmind.api.model.Deployment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/deployments")
public class DeploymentController {

    private final List<Deployment> deployments = new ArrayList<>();

    public DeploymentController() {
        // Sample deployments for demonstration
        deployments.add(new Deployment(5001L, "opsmind-api", "1.0.0", "development", "SUCCESS", "azure-devops"));
        deployments.add(new Deployment(5002L, "opsmind-api", "0.9.0", "development", "SUCCESS", "azure-devops"));
    }

    @GetMapping
    public List<Deployment> getAllDeployments() {
        return deployments;
    }

    @GetMapping("/{id}")
    public Deployment getDeploymentById(@PathVariable Long id) {
        return deployments.stream()
                .filter(deployment -> deployment.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Deployment not found with id: " + id));
    }

    @PostMapping
    public Deployment createDeployment(@RequestBody Deployment deployment) {
        deployments.add(deployment);
        return deployment;
    }
}

package com.opsmind.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IncidentController.class)
class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllIncidentsShouldReturn200() throws Exception {

        mockMvc.perform(get("/api/incidents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1001))
                .andExpect(jsonPath("$[1].id").value(1002));
    }

    @Test
    void getIncidentByIdShouldReturnIncident() throws Exception {

        mockMvc.perform(get("/api/incidents/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1001))
                .andExpect(jsonPath("$.title")
                        .value("High API Response Time"))
                .andExpect(jsonPath("$.severity").value("HIGH"));
    }

    @Test
    void getUnknownIncidentShouldReturn404() throws Exception {

        mockMvc.perform(get("/api/incidents/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Resource Not Found"))
                .andExpect(jsonPath("$.message")
                        .value("Incident not found with id: 9999"));
    }

    @Test
    void createValidIncidentShouldReturn200() throws Exception {

        String requestBody = """
                {
                    "id": 1004,
                    "title": "Database Connection Issue",
                    "service": "opsmind-api",
                    "severity": "HIGH",
                    "status": "OPEN",
                    "description": "Application is unable to connect to database"
                }
                """;

        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1004))
                .andExpect(jsonPath("$.severity").value("HIGH"));
    }

    @Test
    void createIncidentWithInvalidSeverityShouldReturn400()
            throws Exception {

        String requestBody = """
                {
                    "id": 1005,
                    "title": "High CPU",
                    "service": "opsmind-api",
                    "severity": "ABC",
                    "status": "OPEN",
                    "description": "CPU utilization is too high"
                }
                """;

        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error")
                        .value("Validation Failed"))
                .andExpect(jsonPath("$.errors.severity")
                        .value("Severity must be one of: LOW, MEDIUM, HIGH, CRITICAL"));
    }
}
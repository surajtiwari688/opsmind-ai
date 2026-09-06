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

@WebMvcTest(DeploymentController.class)
class DeploymentControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        void getAllDeploymentsShouldReturn200() throws Exception {

                mockMvc.perform(get("/api/deployments"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].id").value(5001))
                                .andExpect(jsonPath("$[1].id").value(5002));
        }

        @Test
        void getDeploymentByIdShouldReturnDeployment() throws Exception {

                mockMvc.perform(get("/api/deployments/5001"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(5001))
                                .andExpect(jsonPath("$.application")
                                                .value("opsmind-api"))
                                .andExpect(jsonPath("$.version").value("1.0.0"))
                                .andExpect(jsonPath("$.status").value("SUCCESS"));
        }

        @Test
        void getUnknownDeploymentShouldReturn404() throws Exception {

                mockMvc.perform(get("/api/deployments/9999"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.status").value(404))
                                .andExpect(jsonPath("$.error")
                                                .value("Resource Not Found"))
                                .andExpect(jsonPath("$.message")
                                                .value("Deployment not found with id: 9999"));
        }

        @Test
        void createDeploymentShouldReturn200() throws Exception {

                String requestBody = """
                                {
                                    "id": 5003,
                                    "application": "opsmind-api",
                                    "version": "1.1.0",
                                    "environment": "development",
                                    "status": "SUCCESS",
                                    "deployedBy": "azure-devops"
                                }
                                """;

                mockMvc.perform(post("/api/deployments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(5003))
                                .andExpect(jsonPath("$.version").value("1.1.0"))
                                .andExpect(jsonPath("$.status").value("SUCCESS"));
        }
}
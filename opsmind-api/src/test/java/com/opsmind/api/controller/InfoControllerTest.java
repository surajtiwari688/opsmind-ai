package com.opsmind.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void infoEndpointShouldReturnApplicationInformation() throws Exception {

        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.application")
                        .value("OpsMind AI"))
                .andExpect(jsonPath("$.version")
                        .value("1.0.0"))
                .andExpect(jsonPath("$.description")
                        .value("An AI-powered DevOps Incident & Observability Platform"))
                .andExpect(jsonPath("$.environment")
                        .value("development"))
                .andExpect(jsonPath("$.status")
                        .value("running"));
    }
}
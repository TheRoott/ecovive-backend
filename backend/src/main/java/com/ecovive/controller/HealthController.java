package com.ecovive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de salud para Railway
 * Endpoint simple para healthcheck
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "EcoVive Perú Backend");
        response.put("version", "1.0.0");
        return response;
    }

    @GetMapping("/")
    public Map<String, String> root() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "🌱 EcoVive Perú Backend está funcionando!");
        response.put("api", "/api");
        response.put("docs", "/api/swagger-ui.html");
        response.put("health", "/api/actuator/health");
        return response;
    }
}

package com.ecovive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    
    @GetMapping("/")
    public String home() {
        return "EcoVive Backend funcionando!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}

package com.example.authdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {
    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
                "username", authentication.getName(),
                "authorities", authentication.getAuthorities()
        );
    }

    @GetMapping("/admin/health")
    public Map<String, String> adminHealth() {
        return Map.of("status", "admin endpoint is secured");
    }
}

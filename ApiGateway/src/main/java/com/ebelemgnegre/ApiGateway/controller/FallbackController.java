package com.ebelemgnegre.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public String userServiceFallback() {
        return "User service is currently unavailable. Please try again later.";
    }

    @GetMapping("/movieServiceFallback")
    public String movieServiceFallback() {
        return "Movie service is currently unavailable. Please try again later.";
    }
}

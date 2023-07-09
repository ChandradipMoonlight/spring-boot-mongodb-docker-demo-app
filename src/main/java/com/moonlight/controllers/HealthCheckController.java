package com.moonlight.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthCheckController {

    @GetMapping(value = "/check")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("Application Working", HttpStatus.OK);
    }
}

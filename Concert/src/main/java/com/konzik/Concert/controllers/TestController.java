package com.konzik.Concert.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/concert/test")
public class TestController {

    @GetMapping("/public")
    public String allAccess() {
        return "Public Content of Concert microservice.";
    }
}

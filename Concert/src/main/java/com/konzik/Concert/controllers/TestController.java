package com.konzik.Concert.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {

    @GetMapping("/public")
    public String allAccess() {
        return "Public Content of Concert microservice.";
    }
}

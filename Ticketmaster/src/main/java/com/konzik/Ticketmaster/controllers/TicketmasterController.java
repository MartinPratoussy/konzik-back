package com.konzik.Ticketmaster.controllers;

import com.konzik.Ticketmaster.payloads.request.ApiRequest;
import com.konzik.Ticketmaster.services.TicketmasterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/ticketmaster")
public class TicketmasterController {

    @Autowired
    private final TicketmasterService service;

    public TicketmasterController(TicketmasterService service) {
        this.service = service;
    }

    @PostMapping("/events")
    public String getEvents(@Valid @RequestBody ApiRequest request) throws IOException {

        return service.buildRequest(request.getFilters());
    }
}

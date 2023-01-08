package com.konzik.Concert.controllers;

import com.konzik.Concert.payload.request.AddConcertRequest;
import com.konzik.common.payloads.MessageResponse;
import com.konzik.common.entities.Concert;
import com.konzik.Concert.services.ConcertService;
import com.konzik.common.repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/concert")
public class ConcertController {

    @Autowired
    private ConcertService service;

    @Autowired
    private ConcertRepository repository;

    ConcertController(ConcertService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Concert> concerts(@ModelAttribute Concert concert, Model model) {
        return service.allConcert();
    }

    @GetMapping("/all/find/{id}")
    public Concert findConcertById(@PathVariable("id") UUID id) {
        return service.findConcertById(id);
    }

    @DeleteMapping("/all/delete/{id}")
    public ResponseEntity<MessageResponse> deleteConcert(@RequestParam String id) {
        service.deleteConcert(id);
        return ResponseEntity.ok(new MessageResponse("Concert deleted successfully!"));
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addConcert(@Valid @RequestBody AddConcertRequest addConcertRequest) throws Exception {
        service.addConcert(addConcertRequest);
        return ResponseEntity.ok(new MessageResponse("Concert added successfully!"));
    }
}
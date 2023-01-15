package com.konzik.Concert.controllers;

import com.konzik.Concert.payload.request.AddConcertRequest;
import com.konzik.Concert.services.ConcertService;
import com.konzik.common.entities.Concert;
import com.konzik.common.payloads.MessageResponse;
import com.konzik.common.repositories.ConcertRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MessageResponse> deleteConcert(@PathVariable String id) {
        service.deleteConcert(id);
        return ResponseEntity.ok(new MessageResponse("Concert deleted successfully!"));
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addConcert(@Valid @RequestBody AddConcertRequest addConcertRequest) throws Exception {
        service.addConcert(addConcertRequest);
        return ResponseEntity.ok(new MessageResponse("Concert added successfully!"));
    }

    @GetMapping("/users/{username}/all")
    public List<Concert> userPlanning(@PathVariable String username) {
        return service.userPlanning(username);
    }

    @PostMapping("/users/{username}/add/{id}")
    public ResponseEntity<MessageResponse> addFromExisting(@PathVariable String username, @PathVariable UUID id) {
        service.addConcertFromExisting(username, id);
        return ResponseEntity.ok(new MessageResponse("Existing concert added to user planning successfully!"));
    }

    @DeleteMapping("/users/{username}/remove/{id}")
    public ResponseEntity<MessageResponse> removeFromUserPlanning(@PathVariable String username, @PathVariable UUID id) {
        service.removeConcertFromUserPlanning(username, id);
        return ResponseEntity.ok(new MessageResponse("Concert removed from user planning successfully!"));
    }
}
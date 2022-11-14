package com.konzik.Concert.controllers;

import com.konzik.Common.Concert;
import com.konzik.Concert.services.ConcertService;
import com.konzik.Concert.Repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/concert")
public class ConcertController {

    @Autowired
    private ConcertService service;

    @Autowired
    private ConcertRepository repository;

    ConcertController(ConcertService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/planning";
    }

    @GetMapping("/all")
    public String concerts(@ModelAttribute Concert concert, Model model) {
        model.addAttribute("concertRecurrence", service.getRecurrence());
        model.addAttribute("concerts", service.allConcert());
        model.addAttribute("concertToAdd", concert);
        return "concerts";
    }

    @GetMapping("/all/find/{id}")
    public Concert findConcertById(@PathVariable("id") Long id) {
        return service.findConcertById(id);
    }

    @PostMapping(
            path="/all",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String newConcert(Concert concert) throws Exception {
        service.addConcert(concert);
        return "redirect:/planning";
    }

    @GetMapping("/add")
    public String concertForm(@ModelAttribute Concert concert, Model model) {
        model.addAttribute("concert", concert);
        return "concertForm";
    }

    @PostMapping("/all/find/{id}/delete")
    public String deleteConcert(@RequestParam String id) {
        service.deleteConcert(id);
        return "redirect:/planning";
    }
}

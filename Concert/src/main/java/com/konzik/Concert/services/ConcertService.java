package com.konzik.Concert.services;

import com.konzik.common.entities.Concert;
import com.konzik.common.repositories.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConcertService {

    @Autowired
    private final ConcertRepository repository;

    @Autowired
    ConcertService(ConcertRepository repository) {
        this.repository = repository;
    }

    public Iterable<Concert> allConcert() {
        return repository.findAll();
    }

    public void addConcert(Concert concert) {
        if (repository.findById(concert.getId()).isPresent()) {
            return;
        }
        System.out.println(concert.toString());
        repository.save(concert);
    }

    public void deleteConcert(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    public Concert findConcertById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NullPointerException("concert not found"));
    }

    public Map<UUID, Long> getRecurrence() {
        Map<UUID, Long> concertRecurrence = new HashMap<>();
        for (Concert concert : allConcert()) {
            UUID concertId = concert.getId();
            Long recurrence = repository.getConcertRecurrence(concertId);
            if (recurrence == 0) {
                repository.deleteById(concertId);
                repository.flush();
            } else {
                concertRecurrence.put(concertId, recurrence);
            }
        }

        return concertRecurrence;
    }
}

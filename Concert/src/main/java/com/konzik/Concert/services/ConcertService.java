package com.konzik.Concert.services;

import com.konzik.Common.Concert;
import com.konzik.Concert.Repositories.ConcertRepository;
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
        repository.deleteById(Long.parseLong(id));
    }

    public Concert findConcertById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NullPointerException("concert not found"));
    }

    public Map<Long, Long> getRecurrence() {
        Map<Long, Long> concertRecurrence = new HashMap<>();
        for (Concert concert : allConcert()) {
            Long concertId = concert.getId();
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

package com.konzik.Concert.services;

import com.konzik.Concert.payload.request.AddConcertRequest;
import com.konzik.common.entities.Concert;
import com.konzik.common.entities.User;
import com.konzik.common.repositories.ConcertRepository;
import com.konzik.common.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConcertService {

    @Autowired
    private final ConcertRepository concertRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    ConcertService(ConcertRepository concertRepository, UserRepository userRepository) {
        this.concertRepository = concertRepository;
        this.userRepository = userRepository;
    }

    public List<Concert> allConcert() {
        return concertRepository.findAll();
    }

    public void addConcert(AddConcertRequest concert) {
        Concert newConcert = new Concert(
                concert.getDate(),
                concert.getArtist(),
                concert.getGenre(),
                concert.getLocation(),
                concert.getCity(),
                concert.getCountry()
        );

        concertRepository.save(newConcert);

        User user = userRepository.findByUsername(concert.getRequestSenderUsername());
        user.addConcertToPlanning(newConcert);
    }

    public void deleteConcert(String id) {
        concertRepository.deleteById(UUID.fromString(id));
    }

    public Concert findConcertById(UUID id) {
        return concertRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("concert not found"));
    }

    public List<Concert> userPlanning(String username) {
        User user = userRepository.findByUsername(username);
        return user.getPlanning();
    }

    public void removeConcertFromUserPlanning(String username, UUID id) {
        User user = userRepository.findByUsername(username);
        user.removeConcertFromPlanning(id);
    }

    /*public Map<UUID, Long> getRecurrence() {
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
    }*/
}

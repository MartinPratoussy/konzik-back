package com.konzik.Concert.Repositories;

import com.konzik.Common.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {

    String concertApiUrl = "http//localhost:8080/concerts";

    Optional<Concert> findById(Long id);

    @Query("SELECT COUNT(concert_id) FROM Concert c JOIN c.users u WHERE c.id = :id")
    Long getConcertRecurrence(@Param("id") Long id);
}

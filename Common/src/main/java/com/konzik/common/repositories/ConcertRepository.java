package com.konzik.common.repositories;

import com.konzik.common.entities.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, UUID> {

    Concert findConcertById(UUID id);

    /*@Query("SELECT COUNT(concert_id) FROM Concert c JOIN c.users u WHERE c.id = :id")
    Long getConcertRecurrence(@Param("id") UUID id);*/
}

package com.konzik.common.repositories;

import com.konzik.common.entities.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, UUID> {

    Optional<Concert> findById(UUID id);

    @Query("SELECT COUNT(concert_id) FROM Concert c JOIN c.users u WHERE c.id = :id")
    Long getConcertRecurrence(@Param("id") UUID id);
}

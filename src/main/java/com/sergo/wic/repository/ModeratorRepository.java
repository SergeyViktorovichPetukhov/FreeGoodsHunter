package com.sergo.wic.repository;

import com.sergo.wic.entities.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    Optional<Moderator> findByLogin(String login);
    boolean existsByLogin(String login);
}

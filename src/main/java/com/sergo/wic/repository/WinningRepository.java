package com.sergo.wic.repository;

import com.sergo.wic.entities.Winning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WinningRepository extends JpaRepository<Winning, Long> {
    Optional<Winning> findByUser_Login(String userLogin);
}
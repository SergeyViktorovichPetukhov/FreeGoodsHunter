package com.sergo.wic.service;

import com.sergo.wic.entities.Winning;

import java.util.Optional;

public interface WinningService {
    Optional<Winning> findByUserLogin(String userLogin);
}

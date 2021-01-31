package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Winning;
import com.sergo.wic.repository.WinningRepository;
import com.sergo.wic.service.WinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WinningServiceImpl implements WinningService {

    private WinningRepository repository;

    public WinningServiceImpl(@Autowired WinningRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<Winning> findByUserLogin(String userLogin) {
        return repository.findByUser_Login(userLogin);
    }
}

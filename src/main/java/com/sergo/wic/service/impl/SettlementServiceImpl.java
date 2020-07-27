package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Settlement;
import com.sergo.wic.repository.SettlementRepository;
import com.sergo.wic.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettlementServiceImpl implements SettlementService {

    private SettlementRepository repository;

    public SettlementServiceImpl(@Autowired SettlementRepository repository){

        this.repository = repository;
    }

    @Override
    public Optional<Settlement> findByNameAndCountry(String sName, String country) {
        return repository.findBysNameAndCountry(sName,country);
    }

    @Override
    public Integer getMaxCountItems(String sName, String country) {
        return repository.getMaxCountItems(sName,country);
    }
}

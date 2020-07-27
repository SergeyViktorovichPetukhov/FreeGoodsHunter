package com.sergo.wic.service;


import com.sergo.wic.entities.Settlement;

import java.util.Optional;

public interface SettlementService {
    Optional<Settlement> findByNameAndCountry(String sName, String country);
    Integer getMaxCountItems(String sName, String country);

}

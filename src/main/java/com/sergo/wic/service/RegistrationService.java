package com.sergo.wic.service;

import com.sergo.wic.entities.Registration;

import java.util.List;

public interface RegistrationService {
    Registration findByLogin(String login);
    Registration findByPhone(String phone);
    Registration findByUserId(Long id);
    boolean deleteByLogin(String login);
    boolean deleteByPhone(String phone);
    Registration save(Registration registration);
    List<Registration> findAll();
}

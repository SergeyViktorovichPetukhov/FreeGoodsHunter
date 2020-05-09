package com.sergo.wic.service;

import com.sergo.wic.entities.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    Registration findByLogin(String login);
    Registration findByPhone(String phone);
    Registration findByUserId(Long id);
    Optional<Registration> findByCodeAndLogin(String code, String login);
    boolean deleteByLogin(String login);
    boolean deleteByPhone(String phone);
    Registration save(Registration registration);
    void refuseRegistration(String id, String reason);
    List<Registration> findAll();
}

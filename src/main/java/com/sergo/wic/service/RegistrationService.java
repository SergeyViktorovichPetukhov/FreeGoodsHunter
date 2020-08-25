package com.sergo.wic.service;

import com.sergo.wic.entities.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    Registration findByLogin(String login);
    Registration findByPhone(String phone);
    Optional<Registration> findByRegId(String regId);
    Registration findByUserId(Long id);
    Optional<Registration> findByCodeAndLogin(String code, String login);
    boolean deleteByLogin(String login);
    boolean deleteByPhone(String phone);
    Registration save(Registration registration);
    void refuseRegistration(String regId, String reason, String emailTo);
    List<Registration> findAll();
}

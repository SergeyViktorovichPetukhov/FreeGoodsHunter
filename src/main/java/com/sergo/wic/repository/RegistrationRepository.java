package com.sergo.wic.repository;

import com.sergo.wic.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    Registration findByLogin(String login);
    Optional<Registration> findByRegId(String regId);
    Registration findByContact(String c);
    Registration findByUserId(Long userId);
    Registration findByCode(String code);
    Optional<Registration> findByCodeAndLogin(String code, String login);
    boolean deleteByLogin(String login);
    boolean deleteByContact(String c);
}

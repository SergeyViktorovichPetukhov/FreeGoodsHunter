package com.sergo.wic.repository;

import com.sergo.wic.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    Registration findByLogin(String login);
    Registration findByPhone(String phone);
    Registration findByUserId(Long userId);
    boolean deleteByLogin(String login);
    boolean deleteByPhone(String phone);
}

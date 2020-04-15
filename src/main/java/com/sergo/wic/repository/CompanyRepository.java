package com.sergo.wic.repository;

import com.sergo.wic.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByLogin(String login);
    Company findByPhone(String phone);
}

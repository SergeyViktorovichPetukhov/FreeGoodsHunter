package com.sergo.wic.service;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;

import java.util.Optional;

public interface CompanyService {
    Company save(Company company);

    Company findByPhone(String phone);

    Optional<Company> findByLogin(String login);

    Company findById(long id);

    Optional<Company> findByCompanyId(String companyId);
}

package com.sergo.wic.service;

import com.sergo.wic.entities.Company;

public interface CompanyService {
    public Company save(Company company);
    public Company findByPhone(String phone);
}

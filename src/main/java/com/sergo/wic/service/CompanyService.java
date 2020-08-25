package com.sergo.wic.service;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;

public interface CompanyService {
    public Company save(Company company);

    public Company findByPhone(String phone);

    public Company findById(long id);
}

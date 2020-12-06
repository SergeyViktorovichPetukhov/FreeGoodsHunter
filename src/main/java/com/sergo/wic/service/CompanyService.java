package com.sergo.wic.service;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;

public interface CompanyService {
    Company save(Company company);

    Company findByPhone(String phone);

    Company findById(long id);
}

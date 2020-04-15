package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Company;
import com.sergo.wic.repository.CompanyRepository;
import com.sergo.wic.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository repository;

    @Override
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    public Company findByPhone(String phone){
        return repository.findByPhone(phone);
    }
}

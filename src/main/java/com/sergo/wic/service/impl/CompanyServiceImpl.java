package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import com.sergo.wic.repository.CompanyRepository;
import com.sergo.wic.repository.ShareRepository;
import com.sergo.wic.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ShareRepository shareRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findByPhone(String phone){
        return companyRepository.findByContact(phone);
    }

    @Override
    public Company findById(long id) {
        return companyRepository.findById(id).orElseGet(null);
    }

}

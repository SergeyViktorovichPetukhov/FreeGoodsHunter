package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Registration;
import com.sergo.wic.repository.RegistrationRepository;
import com.sergo.wic.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository repository;

    @Override
    public Registration findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Registration findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    @Override
    public Registration findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public boolean deleteByLogin(String login) {
        return repository.deleteByLogin(login);
    }

    @Override
    @Transactional
    public boolean deleteByPhone(String phone) {
        return repository.deleteByPhone(phone);

    }

    @Override
    @Transactional
    public Registration save(Registration registration) {
        return repository.save(registration);
    }

    @Override
    public List<Registration> findAll(){
        return repository.findAll();
    }
}

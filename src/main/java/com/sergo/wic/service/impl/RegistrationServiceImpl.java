package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Registration;
import com.sergo.wic.entities.enums.RegistrationState;
import com.sergo.wic.repository.RegistrationRepository;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository repository;

    @Autowired
    EmailService emailService;

    @Override
    public Registration findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Registration findByPhone(String phone) {
        return repository.findByContact(phone);
    }

    @Override
    public Registration findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public Optional<Registration> findByCodeAndLogin(String code, String login) {
        return repository.findByCodeAndLogin(code,login);
    }

    @Override
    public boolean deleteByLogin(String login) {
        return repository.deleteByLogin(login);
    }

    @Override
    @Transactional
    public boolean deleteByPhone(String phone) {
        Registration registration = repository.findByContact(phone);
        if (registration != null){
            repository.deleteById(registration.getId());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Registration> findByRegId(String regId) {
        return repository.findByRegId(regId);
    }

    @Override
    @Transactional
    public Registration save(Registration registration) {
        return repository.save(registration);
    }

    @Override
    public void refuseRegistration(String regId, String reason, String to) {
        Optional<Registration> registration = repository.findByRegId(regId);
        if (registration.isPresent()){
            Registration r = registration.get();
            r.setReasonOfRefuse(reason);
            r.setState(RegistrationState.REFUSED);
            emailService.sendSimpleMessage(to,"refuse registration","reason : "+ reason);
            repository.save(r);
        }
    }

    @Override
    public List<Registration> findAll(){
        return repository.findAll();
    }
}

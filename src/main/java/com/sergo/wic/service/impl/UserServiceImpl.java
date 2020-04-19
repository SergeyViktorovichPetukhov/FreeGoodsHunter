package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Registration;
import com.sergo.wic.entities.User;
import com.sergo.wic.repository.UserRepository;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserService;
import com.sergo.wic.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrationService registrationService;

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public User findByLogin(String login){
        return repository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("no such user"));
    }

    @Override
    public User findByPhone(String phone){
        return repository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("no such user"));
    }

    @Override
    public void save(User user){
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void confirmRegistration(@NotNull User user) {
        user.setConfirmed(true);
        repository.save(user);
        Registration registration = registrationService.findByUserId(Long.valueOf(user.getId()));
        registration.setNew(false);
        registrationService.save(registration);
    }

    @Override
    public void refuseRegistration(@NotNull User user, String reason) {
        emailService.sendSimpleMessage(user.getEmail(),"registration refused",reason);
    }

    @Override
    public void deleteUserCompany(Long userId){
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("no such user"));
        user.setConfirmed(false);
        repository.save(user);

    }

    @Override
    public void deleteUser(Long userId){
        User user = repository.findById(userId).get();
        repository.delete(user);
    }
}

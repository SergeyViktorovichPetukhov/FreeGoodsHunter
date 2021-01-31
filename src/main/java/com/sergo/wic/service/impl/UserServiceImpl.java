package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Registration;
import com.sergo.wic.entities.Winning;
import com.sergo.wic.entities.enums.RegistrationState;
import com.sergo.wic.entities.User;
import com.sergo.wic.repository.UserRepository;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserService;
import com.sergo.wic.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CompanyService companyService;

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login){
        return repository.findByLogin(login);
    }

    @Override
    public User findByContact(String contact){
        return repository.findByContact(contact)
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
    public User getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void confirmRegistration(@NotNull User user, String regID) {
        user.setHasCompany(true);
        user.setCompanyRegInProcess(false);
        repository.save(user);
        Registration registration = registrationService.findByRegId(regID).get();
        registration.setState(RegistrationState.CONFIRMED);
        emailService.sendSimpleMessage(user.getLogin(),"registration is confirmed","your application is approved! Your verification code: " + registration.getCode());
        registrationService.save(registration);
    }

    @Override
    public void refuseRegistration(@NotNull User user, String reason) {
        emailService.sendSimpleMessage(user.getLogin(),"registration refused",reason);

    }

    @Override
    public void deleteUserCompany(Long userId){
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("no such user"));
        user.setHasCompany(false);
        repository.save(user);

    }

    @Override
    public void deleteUser(Long userId){
        User user = repository.findById(userId).get();
        repository.delete(user);
    }

    @Transactional
    @Override
    public boolean hasPendingWinnings(String login) {
        Optional<User> user = repository.findByLogin(login);
        if (user.isPresent()){
            User u = user.get();

            boolean hasPendingWinnings = u.getWinnings()
                    .stream()
                    .anyMatch(winning -> !winning.isViewed());

            List<Winning> winnings = u.getWinnings()
                    .stream()
                    .peek(winning -> winning.setViewed(true))
                    .collect(Collectors.toList());
            u.setWinnings(winnings);

            return hasPendingWinnings;
        }
        return false;
    }
}

package com.sergo.wic.service.impl;

import com.sergo.wic.entities.*;
import com.sergo.wic.entities.enums.RegistrationState;
import com.sergo.wic.entities.enums.ShareCellType;
import com.sergo.wic.entities.enums.ShareState;
import com.sergo.wic.repository.UserRepository;
import com.sergo.wic.repository.WinningRepository;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserService;
import com.sergo.wic.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CompanyService companyService;


    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByContact(String contact){
        return userRepository.findByContact(contact)
                .orElseThrow(() -> new RuntimeException("no such user"));
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void confirmRegistration(@NotNull User user, String regID) {
        user.setHasCompany(true);
        user.setCompanyRegInProcess(false);
        userRepository.save(user);
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("no such user"));
        user.setHasCompany(false);
        userRepository.save(user);

    }

    @Override
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public boolean hasPendingWinnings(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()){
            User u = user.get();

            return u.getWinnings()
                    .stream()
                    .anyMatch(winning -> !winning.getIsReviewed());
        }
        return false;
    }

    @Override
    public boolean hasUnreadNotifications(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()){
            User u = user.get();

            return u.getNotifications()
                    .stream()
                    .anyMatch(notification -> !notification.getRead());
        }
        return false;
    }

    @Transactional
    @Override
    public Optional<List<Notification>> getNotifications(User user) {
        List<Notification> notifications = user.getNotifications()
                .stream()
                .peek(notification -> notification.setRead(true))
                .collect(Collectors.toList());
        user.setNotifications(notifications);
        return Optional.of(notifications);

    }

    @Override
    public Optional<List<Winning>> getWinnings(User user) {
        List<Winning> winnings = user.getWinnings().stream()
                .peek(winning -> winning.setIsReviewed(true))
                .collect(Collectors.toList());
        user.setWinnings(winnings);
        return Optional.of(winnings);
    }

    //TODO wrong relationships in DB
    @Override
    public List<Winning> getAllWinnings( ) {
        return winningRepository.findAll();
//        List<User> users = userRepository.findAll();
//        return users.stream()
//                .map(User::getWinnings)
//                .reduce((list1, list2) -> {
//                    list1.addAll(list2);
//                    return list1;
//                })
//                .orElse(null);
    }

    @Transactional
    @Override
    public Optional<List<ShareCellType>> getShareCellTypes(User user, List<Share> allShares) {

        List<Share> chosenShares = user.getChosenShares();
        List<Share> startedShares = user.getStartedShares();

        allShares.addAll(chosenShares);
        allShares.addAll(startedShares);

        List<ShareCellType> types = allShares.stream()
                .map(share -> {
                    if (startedShares.contains(share)){
                        return ShareCellType.STARTED;
                    } else if (chosenShares.contains(share)){
                        return ShareCellType.CHOSEN;
                    } else if (share.getStatus() == ShareState.ACTIVE){
                        return ShareCellType.ACTIVE;
                    } else if (share.getStatus() == ShareState.PREVIEW){
                        return ShareCellType.PREVIEW;
                    } else {
                        return ShareCellType.FINISHED;
                    }
                })
                .collect(Collectors.toList());

        return Optional.of(types);
    }
}

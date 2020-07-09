package com.sergo.wic.service;

import com.sergo.wic.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);
    Optional<User> findByLogin(String login);
    User findByContact(String contact);
    User getOne(Long id);
    void save(User user);
    List<User> findAll();
    void confirmRegistration(User user);
    void refuseRegistration(User user, String reason);
    void deleteUserCompany(Long userId);
    void deleteUser(Long userId);
}

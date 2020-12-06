package com.sergo.wic.service.impl;

import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserProfile;
import com.sergo.wic.repository.UserProfileRepository;
import com.sergo.wic.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository repository;

    @Override
    public UserProfile save(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    @Override
    public void delete(UserProfile userProfile) {
        repository.delete(userProfile);
    }

    @Override
    public UserProfile findByUser(User user) {
        return repository.findByUser(user);
    }
}

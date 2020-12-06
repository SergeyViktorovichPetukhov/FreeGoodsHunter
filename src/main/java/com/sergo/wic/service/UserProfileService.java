package com.sergo.wic.service;

import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserProfile;

public interface UserProfileService {
    UserProfile save(UserProfile userProfile);
    void delete(UserProfile userProfile);
    UserProfile findByUser(User user);
}


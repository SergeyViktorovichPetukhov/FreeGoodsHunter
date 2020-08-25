package com.sergo.wic.service;

import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserItem;

import java.util.List;
import java.util.Optional;

public interface UserItemService {
    boolean save(UserItem userItem);
    Optional<UserItem> findByUser(User user);
    Optional<UserItem> findByUserAndShare(User user, Share share);
    List<UserItem> findAll();
}

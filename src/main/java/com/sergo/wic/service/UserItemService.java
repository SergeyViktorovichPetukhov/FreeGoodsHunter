package com.sergo.wic.service;

import com.sergo.wic.entities.UserItem;

import java.util.List;

public interface UserItemService {
    boolean save(UserItem userItem);
    List<UserItem> findAll();
}

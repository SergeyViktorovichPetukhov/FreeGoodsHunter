package com.sergo.wic.service.impl;

import com.sergo.wic.entities.UserItem;
import com.sergo.wic.repository.UserItemRepository;
import com.sergo.wic.service.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserItemServiceImpl implements UserItemService {

    private UserItemRepository repository;

    public UserItemServiceImpl(@Autowired UserItemRepository repository){
        this.repository = repository;
    }

    @Override
    public boolean save(UserItem userItem) {
        if (repository.save(userItem) != null)
            return true;
        else return false;
    }

    @Override
    public List<UserItem> findAll() {
        return repository.findAll();
    }
}

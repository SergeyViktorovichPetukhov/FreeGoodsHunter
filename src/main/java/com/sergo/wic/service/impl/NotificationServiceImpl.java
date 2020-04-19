package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Notification;
import com.sergo.wic.entities.User;
import com.sergo.wic.repository.NotificationRepository;
import com.sergo.wic.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository repository;

    public  NotificationServiceImpl(@Autowired NotificationRepository repository){
        this.repository = repository;
    }

    @Override
    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    @Override
    public List<Notification> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Notification> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public Notification findByMessage(String message) {
        return repository.findByMessage(message);
    }

    @Override
    public Notification findByUser(User user) {
        return repository.findByUser(user);
    }
}

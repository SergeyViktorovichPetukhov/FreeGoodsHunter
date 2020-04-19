package com.sergo.wic.service;

import com.sergo.wic.entities.Notification;
import com.sergo.wic.entities.User;


import java.util.List;

public interface NotificationService {
    List<Notification> findAll();
    List<Notification> findAllByUser(User user);
    Notification findByMessage(String message);
    Notification findByUser(User user);
    Notification save(Notification notification);
}

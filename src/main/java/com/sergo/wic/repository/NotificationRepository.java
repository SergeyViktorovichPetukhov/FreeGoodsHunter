package com.sergo.wic.repository;

import com.sergo.wic.entities.Notification;

import com.sergo.wic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByUser(User user);
    Notification findByMessage(String message);
    List<Notification> findAllByUser(User user);
}

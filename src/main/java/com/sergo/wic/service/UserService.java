package com.sergo.wic.service;

import com.sergo.wic.entities.Notification;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.Winning;
import com.sergo.wic.entities.enums.ShareCellType;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);
    Optional<User> findByLogin(String login);
    User findByContact(String contact);
    User getOne(Long id);
    void save(User user);
    List<User> findAll();
    void confirmRegistration(User user, String regId);
    void refuseRegistration(User user, String reason);
    void deleteUserCompany(Long userId);
    void deleteUser(Long userId);
    boolean hasPendingWinnings(String login);
    boolean hasUnreadNotifications(String login);
    Optional<List<Notification>> getNotifications(User user);
    Optional<List<Winning>> getWinnings(User user);
    Optional<List<ShareCellType>> getShareCellTypes(User user, List<Share> allShares);
}

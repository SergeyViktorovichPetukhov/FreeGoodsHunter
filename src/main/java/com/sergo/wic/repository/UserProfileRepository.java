package com.sergo.wic.repository;

import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    //@Query("SELECT up AS UserProfile ")
    UserProfile findByUser(User user);
}

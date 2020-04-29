package com.sergo.wic.repository;

import com.sergo.wic.entities.UserItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemsRepository extends JpaRepository<UserItems, Long> {
}

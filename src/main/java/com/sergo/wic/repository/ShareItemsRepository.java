package com.sergo.wic.repository;

import com.sergo.wic.entities.ShareItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareItemsRepository extends JpaRepository<ShareItems, Long> {
}

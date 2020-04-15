package com.sergo.wic.repository;

import com.sergo.wic.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("shareRepository")
public interface ShareRepository extends JpaRepository<Share, Long> {
}

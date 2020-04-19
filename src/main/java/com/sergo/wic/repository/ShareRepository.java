package com.sergo.wic.repository;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    boolean deleteByShareId(String shareId);
    Share findByShareId(String shareId);
    List<Share> findAllByCompany(Company company);
}

package com.sergo.wic.repository;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    boolean deleteByShareId(String shareId);
    boolean existsByShareId(String shareId);
    Optional<Share> findByLogin(String login);
    Optional<Share> findByShareId(String shareId);
    List<Share> findAllByCompany(Company company);
}

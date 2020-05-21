package com.sergo.wic.repository;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.ShareState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    boolean deleteByShareId(String shareId);
    boolean existsByShareId(String shareId);
    Optional<Share> findByLogin(String login);
    Optional<Share> findByShareId(String shareId);
    List<Share> findAllByCompany(Company company);
    //Query(value = "SELECT * FROM shares WHERE id > :old_id AND company_id = :comp_id;", nativeQuery = true)
    @Query(value = "SELECT * FROM shares WHERE id > :old_id AND creation_status = 'CREATED';", nativeQuery = true)
    Optional<List<Share>> findNewShares(@Param("old_id") Long oldId);


}

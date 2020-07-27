package com.sergo.wic.repository;

import com.sergo.wic.entities.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement,Long> {
    @Query(value = "SELECT max_count_items FROM settlements WHERE s_name = :s_name AND country = :country", nativeQuery = true)
    Integer getMaxCountItems(@Param("s_name") String s_name, @Param("country") String country);
    Optional<Settlement> findBysNameAndCountry(String sName, String country);
}

package com.sergo.wic.repository;

import com.sergo.wic.entities.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement,Long> {
    @Modifying
    @Query(value = "SELECT max_count_items FROM settlements WHERE s_name = :s_name AND region = :region AND country = :country", nativeQuery = true)
    Integer getMaxCountItems(@Param("s_name") String s_name,@Param("region") String region, @Param("country") String country);
//    @Query(value = "SELECT * FROM settlements WHERE s_name = :s_name AND region = :region AND country = :country", nativeQuery = true)
//    Optional<Settlement> findBysNameAndRegionAndCountry(@Param("s_name")String sName,@Param("region") String region,@Param("country") String country );
//    @Query(value = "SELECT * FROM settlements WHERE s_name = :s_name AND country = :country", nativeQuery = true)
//    Optional<Settlement> findBysNameAndCountry(@Param("s_name")String sName,@Param("country") String country );
    Optional<Settlement> findBysNameAndRegionAndCountry(String sName,String region,String country );
    Optional<Settlement> findBysNameAndCountry(String sName, String country );
}

package com.sergo.wic.repository;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    boolean deleteByShareId(String shareId);
    boolean existsByShareId(String shareId);
    Optional<Share> findByLogin(String login);
    Optional<Share> findByShareId(String shareId);
    Optional<List<Share>> findAllByRegionCode(String regionCode);
//  s.productName, s.productPhotoUrl, s.productDescription, s.productPrice, s.productWebsite, c.name, c.contact, c.internetShop, c.logoUrl

    @Query(value = "SELECT s FROM Share s INNER JOIN s.company WHERE s.shareId = ?1")
    Optional<Share> findShareWithCompany(String shareId);
// "INNER JOIN items.userItems.user.login, items.userItems.user.PickedItemsCount, items.userItems.user.userProfile.photoUrl " +
    @Query(value = "SELECT s.items FROM Share s WHERE s.shareId = ?1")
    List<Item> findShareUserItems(String shareId);

//    @Query(value = "SELECT s FROM Share s WHERE s.shareId = ?1")
//    Optional<Share> findShareWithUsers(String shareId);
    List<Share> findAllByCompany(Company company);

    @Query(value = "SELECT * FROM shares WHERE id > :old_id AND creation_status = 'CREATED';", nativeQuery = true)
    Optional<List<Share>> findNewShares(@Param("old_id") Long oldId);


}

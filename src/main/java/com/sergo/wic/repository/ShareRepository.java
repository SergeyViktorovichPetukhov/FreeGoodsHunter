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
//    @Query(value = "SELECT shares.share_id, shares.is_verificated, shares.product_photo_url, " +
//            "shares.product_website, shares.product_name, shares.product_description," +
//            "shares.product_price, shares.announcement_duration, shares.share_duration," +
//            "shares.color, shares.picked_items_count, shares.all_items_count,shares.date," +
//            "companies.login AS login FROM shares INNER JOIN companies ON companies.id = shares.company_id WHERE shares.region_code =?1" , nativeQuery = true)
    @Query(value = "select new Share(s.shareId, s.isVerificated , s.company.login, s.productPhotoUrl, s.productWebsite, s.productName, s.productDescription, s.productPrice," +
            "s.announcementDuration, s.shareDuration, s.color, s.pickedItemsCount, s.allItemsCount, s.date, s.items ) from Share s where s.regionCode = ?1")
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

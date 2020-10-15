package com.sergo.wic.repository;

import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.postgis.PGgeometry;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<List<Item>> findAllByShare(Share share);
    Item findByItemId(String itemId);
    boolean existsByLongitudeAndLatitude(double lon, double lat);

  //  @Query(value = "SELECT * FROM items WHERE ", nativeQuery = true)
  //  List<Item> findAllOrphaned( Long[] ids);
//    @Query(value = "")
//    Item savePickedItem(Item item);
}

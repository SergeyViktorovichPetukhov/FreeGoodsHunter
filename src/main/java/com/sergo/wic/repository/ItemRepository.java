package com.sergo.wic.repository;

import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByShare(Share share);
  //  @Query(value = "SELECT * FROM items WHERE ", nativeQuery = true)
  //  List<Item> findAllOrphaned( Long[] ids);
//    @Query(value = "")
//    Item savePickedItem(Item item);
}

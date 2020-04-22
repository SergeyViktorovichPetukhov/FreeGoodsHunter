package com.sergo.wic.repository;

import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByShare_Id(Long id);
    Item findByShare(Share share);
//    @Query(value = "")
//    Item savePickedItem(Item item);
}

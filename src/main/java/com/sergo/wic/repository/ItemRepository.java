package com.sergo.wic.repository;

import com.sergo.wic.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByShare_Id(Long id);
}

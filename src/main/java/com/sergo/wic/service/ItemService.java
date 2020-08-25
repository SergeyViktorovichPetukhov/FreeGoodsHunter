package com.sergo.wic.service;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    boolean save(Item item, User user, String shareId);
    Item findById(Long id);
    List<Item> findAllByShare(Share share);
    List<Item> findAllOrphaned();
    List<Item> findAll();
    Item findByItemId(String itemId);
    List<ItemDto> convertAllItems(List<Item> list);
    List<ItemDto> getShareItems(Share share);
    boolean isPickedFullItemsForOneProduct();
    Integer getMaxCountItems(String project,String layer);
}

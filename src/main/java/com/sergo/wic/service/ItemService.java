package com.sergo.wic.service;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    Item save(Item item, User user);
    Item findById(Long id);
    List<Item> findAllByShare(Share share);
    List<Item> findAllOrphaned();
    List<Item> findAll();
    List<ItemDto> getShareItems(Share share);
    boolean isPickedFullItemsForOneProduct();
}

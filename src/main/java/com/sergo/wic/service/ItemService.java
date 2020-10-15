package com.sergo.wic.service;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    Item save(Item item, Share share);
    boolean save(Item item, User user, String shareId);
    boolean existsByCoordinates(double lon, double lat);
    Item findById(Long id);
    List<Item> findAll();
    List<Item> addNewShareItems(List<ItemDto> items);
    Item findByItemId(String itemId);
    List<ItemDto> convertAllItems(List<Item> list);
    List<ItemDto> getShareItems(Share share);
    Integer getMaxCountItems(String project,String layer);
    List<ItemDto> getRandomCoordinates(String table, int quantity, int seed);

}

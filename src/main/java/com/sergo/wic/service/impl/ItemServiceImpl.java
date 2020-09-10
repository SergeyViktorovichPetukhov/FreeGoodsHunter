package com.sergo.wic.service.impl;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.dto.CoordinatesDto;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserItem;
import com.sergo.wic.entities.enums.ItemState;
import com.sergo.wic.repository.ItemRepository;
import com.sergo.wic.repository.RandomPointsRepository;
import com.sergo.wic.service.*;
import com.sergo.wic.utils.RandomString;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.postgis.PGgeometry;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;
    private RandomPointsRepository randomPointsRepository;
    private ItemConverter itemConverter;
    private UserItemService userItemService;
    private ShareService shareService;
    public ItemServiceImpl(@Autowired ItemRepository repository,
                           @Autowired ItemConverter itemConverter,
                           @Autowired UserItemService userItemService,
                           @Autowired ShareService shareService,
                           @Autowired RandomPointsRepository randomPointsRepository
                           ){
        this.repository = repository;
        this.randomPointsRepository = randomPointsRepository;
        this.userItemService = userItemService;
        this.itemConverter = itemConverter;
        this.shareService = shareService;
    }

    @Override
    @Transactional
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public Item findById(Long itemId) {
        Optional<Item> item = repository.findById(itemId);
        if (item.isPresent())
           return repository.findById(itemId).get();
        else return null;
    }

    @Override
    public Item findByItemId(String itemId) {
        return repository.findByItemId(itemId);
    }

    @Transactional
    @Override
    public boolean save(Item item, User user, String shareId) {
        Optional<Share> share = shareService.findByShareId(shareId);
        Optional<UserItem> userItem;
        if (share.isPresent()){
            userItem = userItemService.findByUserAndShare(user, share.get());
        }else
            return false;
        UserItem newUserItem;
        if (userItem.isPresent()){
            UserItem ui = userItem.get();
            ui.setUser(user);
            item.setUserItem(ui);
            userItemService.save(userItem.get());
        }else{
            newUserItem = new UserItem(user);
            newUserItem.setUser(user);
            item.setUserItem(newUserItem);
            userItemService.save(newUserItem);
        }
        item.setState(ItemState.PICKED);
        repository.saveAndFlush(item);
        return true;
    }


    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ItemDto> getShareItems(Share share) {
        Optional<List<Item>> items = repository.findAllByShare(share);
        List<Item> result = null;
        if (items.isPresent()){
            result = items.get().stream().filter((item) -> item.getUserItem() == null).collect(Collectors.toList());
            return itemConverter.convertAllItems(result);
        }
        else return null;
    }

    public List<ItemDto> convertAllItems(List<Item> list){
        return itemConverter.convertAllItems(list);
    }

    @Override
    public Integer getMaxCountItems(String projectPath,String layerName){
        return 20;
    }


    @Override
    public List<ItemDto> getRandomCoordinates(String table, int quantity, int seed) {
        List<PGgeometry> points = randomPointsRepository.getRandomCoordinates(table,quantity,seed);
        List<ItemDto> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            String itemId = RandomString.getAlphaNumericString(8);
            CoordinatesDto coordinates = new CoordinatesDto(
                    points
                            .get(i)
                            .getGeometry()
                            .getPoint(0)
                            .y,
                    points.get(i).getGeometry().getPoint(0).x);
            ItemDto item = new ItemDto(coordinates,itemId);
            result.add(item);
        }
        return result;
    }
}

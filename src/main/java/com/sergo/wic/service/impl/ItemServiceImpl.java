package com.sergo.wic.service.impl;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserItem;
import com.sergo.wic.repository.ItemRepository;
import com.sergo.wic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;
    private CompanyService companyService;
    private ItemConverter itemConverter;
    private UserItemService userItemService;
    private ShareService shareService;
    public ItemServiceImpl(@Autowired ItemRepository repository,
                           @Autowired CompanyService companyService,
                           @Autowired ItemConverter itemConverter,
                           @Autowired UserItemService userItemService,
                           @Autowired ShareService shareService
                           ){
        this.repository = repository;
        this.companyService = companyService;
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
            userItemService.save(userItem.get());
            repository.saveAndFlush(item);
            return true;
       //     return repository.save(item);
        }else{
            newUserItem = new UserItem(user);
            newUserItem.setUser(user);
            item.setUserItem(newUserItem);
            userItemService.save(newUserItem);
            repository.saveAndFlush(item);
            return true;
        //    return repository.save(item);
        }
    }

    @Override
    public boolean isPickedFullItemsForOneProduct() {
        return false;
    }

    @Override
    public List<Item> findAllOrphaned() {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ItemDto> getShareItems(Share share) {
        List<Item> items = repository.findAllByShare(share);
        List<Item> result = items.stream().filter((item) -> item.getUserItem() == null).collect(Collectors.toList());
        return itemConverter.convertAllItems(result);
    }

    public List<ItemDto> convertAllItems(List<Item> list){
        return itemConverter.convertAllItems(list);
    }

    @Override
    public Integer getMaxCountItems(String projectPath,String layerName){

        return 20;
    }

    @Override
    public List<Item> findAllByShare(Share share) {
        return repository.findAllByShare(share);
    }
}

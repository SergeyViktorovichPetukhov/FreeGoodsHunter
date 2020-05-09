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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;
    private CompanyService companyService;
    private ItemConverter itemConverter;
    private UserItemService userItemService;

    public ItemServiceImpl(@Autowired ItemRepository repository,
                           @Autowired CompanyService companyService,
                           @Autowired ItemConverter itemConverter,
                           @Autowired UserItemService userItemService
                           ){
        this.repository = repository;
        this.companyService = companyService;
        this.userItemService = userItemService;
        this.itemConverter = itemConverter;
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

    @Transactional
    @Override
    public Item save(Item item, User user) {
        UserItem userItem = new UserItem(user,item);
        item.setUserItem(userItem);
        userItemService.save(userItem);
    //    userService.save(user);
        return repository.save(item);
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

    @Override
    public List<Item> findAllByShare(Share share) {
        return repository.findAllByShare(share);
    }
}

package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.repository.ItemRepository;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;
    private CompanyService companyService;
    private ShareService shareService;

    public ItemServiceImpl(@Autowired ItemRepository repository,
                           @Autowired CompanyService companyService,
                           @Autowired ShareService shareService
                           ){
        this.repository = repository;
        this.companyService = companyService;
    }

    @Override
    @Transactional
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public boolean isPickedFullItemsForOneProduct() {
        return false;
    }

//    @Override
//    public Item findByShare(Share share) {
//        return repository.findByShare(share);
//    }
}

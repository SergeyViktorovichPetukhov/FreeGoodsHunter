package com.sergo.wic.service;

import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;

public interface ItemService {
    Item save(Item item);
    Item findByShare(Share share);
    boolean isPickedFullItemsForOneProduct();
}

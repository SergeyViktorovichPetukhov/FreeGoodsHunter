package com.sergo.wic.controller;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.dto.Response.GetShareItemsResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.ShowItemsResponse;
import com.sergo.wic.dto.ResponseContent;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private ShareService shareService;
    private ItemConverter itemConverter;

    public ItemController(@Autowired ItemService itemService,
                          @Autowired ShareService shareService,
                          @Autowired ItemConverter itemConverter){
        this.itemService = itemService;
        this.shareService = shareService;
        this.itemConverter = itemConverter;
    }

    @RequestMapping(value = "/getShareItems", method = RequestMethod.GET)
    public Response getShareItems(@RequestParam String shareId){
        Optional<Share> share = shareService.findByShareId(shareId);
        if (share.isPresent()){
            return new Response(true,0, new GetShareItemsResponse(itemService.getShareItems(share.get())));
        }
        return new Response(false,1,"no such share");
    }
    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public Response getAllItems(){

        List<Item> items = itemService.findAll();
        if (items != null) {
            List<Item> result = items.stream().filter(
                    (item) -> item.getUserItem() == null).collect(Collectors.toList());
            return new Response(true,0, new ShowItemsResponse(itemConverter.convertAllItems(result)));
        }
        return new Response(false,1,"no shares");
    }

}

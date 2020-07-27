package com.sergo.wic.controller;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.MaxCountItemsDto;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.dto.Response.GetShareItemsResponse;
import com.sergo.wic.dto.Response.MaxCountItemsResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.ShowItemsResponse;
import com.sergo.wic.dto.ResponseContent;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Settlement;
import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.SettlementService;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private ShareService shareService;
    private ItemConverter itemConverter;
    private SettlementService settlementService;

    public ItemController(@Autowired ItemService itemService,
                          @Autowired ShareService shareService,
                          @Autowired ItemConverter itemConverter,
                          @Autowired SettlementService settlementService){
        this.itemService = itemService;
        this.shareService = shareService;
        this.itemConverter = itemConverter;
        this.settlementService = settlementService;
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

    @PostMapping("/getMaxCountItems")
    public Response getMaxCountItems(@RequestBody MaxCountItemsDto dto){
        Optional<Settlement> settlement = settlementService.findByNameAndCountry(dto.getSettlement(),dto.getCountry());
        if (settlement.isPresent())
            return new Response(true,0, new MaxCountItemsResponse(settlement.get().getMaxCountItems()));
        else return new Response(false,1,"no such settlement in db");
    }



}

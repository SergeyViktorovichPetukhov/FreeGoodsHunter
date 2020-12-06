package com.sergo.wic.controller;

import com.sergo.wic.dto.*;
import com.sergo.wic.dto.Response.*;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Settlement;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.enums.ItemState;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.SettlementService;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.service.UserService;
import com.sergo.wic.utils.Constants;
import com.sergo.wic.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private UserService userService;
    private ShareService shareService;
    private SettlementService settlementService;

    public ItemController(@Autowired ItemService itemService,
                          @Autowired ShareService shareService,
                          @Autowired SettlementService settlementService,
                          @Autowired UserService userService){
        this.itemService = itemService;
        this.shareService = shareService;
        this.settlementService = settlementService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getShareItems", method = RequestMethod.GET)
    public Response getShareItems(@RequestParam String shareId){
        Optional<Share> share = shareService.findByShareId(shareId);
        if (share.isPresent()){
            return new Response(true,0, new GetShareItemsResponse(itemService.getShareItems(share.get())));
        }
        return new Response(false,1,"no such share");
    }

    @RequestMapping(value = "/getRandomCoordinates", method = RequestMethod.POST)
    public Response getRandomCoordinates(@RequestBody RandomCoordinatesRequest request){
        String table = "moscow_mkad_polygons";

        if (request.getGeoLocationData().getSettlement().equals("Bryansk")){
            return new Response(true,0, new RandomItemsDto(Constants.BRYANSK_ITEMS));
        }

        int seed = (int)(Math.random()*1000);
        List<ItemDto> items = itemService.getRandomCoordinates(table,request.getQuantity(),seed);
        return new Response(true,0,new RandomItemsDto(items));
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public void addItem(HttpServletResponse response,
                        @RequestParam String lon,
                        @RequestParam String lat)
    {
        String shareId = "company_owner@mail.ru 2020-04-20 #1";
        Optional<Share> share = shareService.findByShareId(shareId);
        if (share.isPresent()){
            if (itemService.existsByCoordinates(
                    Double.valueOf(lon),
                    Double.valueOf(lat))){
                try {
                    response.sendRedirect("/fgh/admin/showItems");
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                return;
            }


            String itemId = RandomString.getAlphaNumericString(7);
            Item item = new Item(Double.valueOf(lat), Double.valueOf(lon), itemId);
            item.setShare(share.get());
            item.setState(ItemState.FREE);
            itemService.save(item);
            try {
                response.sendRedirect("/fgh/admin/showItems");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // temporary
    @RequestMapping(value = "/addItemForDatabase", method = RequestMethod.POST)
    public Response addItem(@RequestBody AddItemDto dto) throws IOException {
            Item item = new Item();
            item.setLatitude(dto.getCoordinates().getCoordinates().getLatitude());
            item.setLongitude(dto.getCoordinates().getCoordinates().getLongitude());
            Share share = shareService.findByShareId(dto.getShareId()).orElse(shareService.findById(1));
            share.addItem(item);
            item.setShare(share);
            String randomItemId = RandomString.getAlphaNumericString(9);
            item.setItemId(randomItemId);
            item.setUserItem(null);
            item.setState(ItemState.FREE);
          //  shareService.saveShare1(share);
            itemService.save(item);
            return new Response(true,
                    0,
                    "point lat:" + dto.getCoordinates().getCoordinates().getLatitude()
                                                                 + ", lon: " + dto.getCoordinates().getCoordinates().getLongitude()
                                                                 + " added to db"   );
    }

    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public Response getAllItems(){

        List<Item> items = itemService.findAll();
        if (items != null) {
            List<Item> result = items.stream().filter(
                    (item) -> item.getUserItem() == null).collect(Collectors.toList());
            return new Response(true,0, new ShowItemsResponse(itemService.convertAllItems(result)));
        }
        return new Response(false,1,"no shares");
    }

    @PostMapping("/getMaxCountItems")
    public Response getMaxCountItems(@RequestBody MaxCountItemsDto dto){
    //    System.out.println(dto.getLogin() + " " + dto.getGeoLocationData().getRegion() + " " + dto.getGeoLocationData().getCountry() + " " + dto.getGeoLocationData().getSettlement());
        Optional<Settlement> settlement;
        if (dto.getGeoLocationData().getRegion() == null) {
            settlement = settlementService.findByNameAndCountry(
                    dto.getGeoLocationData().getSettlement(),
                    dto.getGeoLocationData().getCountry());
        }else
            settlement = settlementService.findByNameAndRegionAndCountry(
                    dto.getGeoLocationData().getSettlement(),
                    dto.getGeoLocationData().getRegion(),
                    dto.getGeoLocationData().getCountry());

        if (settlement.isPresent())
            return new Response(true,0, new MaxCountItemsResponse(settlement.get().getMaxCountItems()));
        else return new Response(false,1,"no such settlement in db");
    }



}

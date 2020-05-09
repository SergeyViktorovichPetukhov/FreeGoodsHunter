package com.sergo.wic.controller;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.PickItemResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.UserResponse;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
//    private UserFacade userFacade;
    private ItemConverter itemConverter;
    private ItemService itemService;
    private CompanyService companyService;
    private ShareService shareService;

    public UserController(@Autowired UserService userService,
                      //    @Autowired UserFacade userFacade,
                          @Autowired ItemConverter itemConverter,
                          @Autowired ItemService itemService,
                          @Autowired CompanyService companyService,
                          @Autowired ShareService shareService
                          ){
        this.companyService = companyService;
        this.itemService = itemService;
  //      this.userFacade = userFacade;
        this.userService = userService;
        this.itemConverter = itemConverter;
        this.shareService = shareService;
    }

    @PostMapping(value = "/userInfo")
    public Response userInfo(@RequestBody LoginDto login){
        Optional<User> user = userService.findByLogin(login.getLogin());
        if (user.isPresent()){
            return new UserResponse(user.get().isHasCompany());
        }
        return new UserResponse(false,true);
    }

    @PostMapping(value = "/pickItem" ,
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response pickItem(@RequestBody PickedItemDto dto){
        Optional<User> user = userService.findByLogin(dto.getUserLogin());
        if (user.isPresent()){
        Item item = itemConverter.convertToModel(dto);
        Share share = item.getShare();
        int pickedItemsCount = share.getPickedItemsCount();
           if (companyService.checkCompanyOwner(share.getLogin(),user.get().getLogin())){
                itemService.save(item,user.get());
                return new PickItemResponse(true,0,pickedItemsCount);
           }
        return new Response(false,1,"you are the owner of this share");
        }
        return new Response(false,2,"no such user");
    }

}

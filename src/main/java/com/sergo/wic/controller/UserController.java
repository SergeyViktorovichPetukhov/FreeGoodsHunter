package com.sergo.wic.controller;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.IsCompanyRegInProcessResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.UserResponse;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.enums.ItemState;
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
    private ItemService itemService;



    public UserController(@Autowired UserService userService,
                          @Autowired ItemService itemService){
        this.itemService = itemService;
        this.userService = userService;
    }

    @PostMapping(value = "/userInfo")
    public Response userInfo(@RequestBody LoginDto login){
        Optional<User> user = userService.findByLogin(login.getLogin());
        if (user.isPresent()){
            return new Response(true,0, new UserResponse(user.get().isHasCompany()));
        }
        User newUser = new User(login.getLogin());
        userService.save(newUser);
        return new Response(false,0,"new user created", new UserResponse(false, true));
    }

    @PostMapping(value = "/registerNewUser")
    public Response registerUser(@RequestBody LoginDto dto){
        Optional<User> user = userService.findByLogin(dto.getLogin());
        if (user.isPresent()){
            return new Response(false,1, "user with such login already exists");
        }
        User newUser = new User();
        newUser.setLogin(dto.getLogin());
        userService.save(newUser);
        return new Response(true,0,"user registered");
    }

    @PostMapping(value = "/isCompanyRegInProcess")
    public Response isCompanyRegInProcess(@RequestBody LoginDto login){
        Optional<User> user = userService.findByLogin(login.getLogin());
        if (user.isPresent()){
            return new Response(true,0, new IsCompanyRegInProcessResponse( user.get().isCompanyRegInProcess()));
        }
        return new Response(false,1,"no such user",new UserResponse(false));
    }

    @PostMapping(value = "/pickItem" ,
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

    public Response pickItem(@RequestBody PickedItemDto dto){
        Item item = itemService.findByItemId(dto.getItemId());
        if (item.getState() != ItemState.FREE)
            return new Response(false,1, "this item is already picked");
        Share share = item.getShare();
        if ( ! share.getLogin().equals(dto.getLogin()) ){
            Optional<User> user = userService.findByLogin(dto.getLogin());
            if (user.isPresent()){
                itemService.save(item, user.get(), share.getShareId());
                return new Response(true,0,"you picked this item");
            }
            return new Response(false,2,"no user with such login");
        }
        return new Response(false,1,"you are the owner of this share");
    }

}

package com.sergo.wic.controller;

import com.sergo.wic.converter.NotificationsConverter;
import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.converter.WinningsConverter;
import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.PendingWinningsResponse;
import com.sergo.wic.dto.Response.*;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.dto.SharesRequestDto;
import com.sergo.wic.entities.*;
import com.sergo.wic.entities.enums.ItemState;
import com.sergo.wic.entities.enums.ShareCellType;
import com.sergo.wic.entities.enums.TypeContact;
import com.sergo.wic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ItemService itemService;
    private WinningService winningService;
    private ShareConverter shareConverter;

    @Autowired
    private ShareService shareService;

    @Autowired
    NotificationsConverter notificationsConverter;

    @Autowired
    WinningsConverter winningsConverter;

    public UserController(@Autowired UserService userService,
                          @Autowired ItemService itemService,
                          @Autowired WinningService winningService,
                          @Autowired ShareConverter shareConverter){
        this.itemService = itemService;
        this.userService = userService;
        this.winningService = winningService;
        this.shareConverter = shareConverter;
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

    @Transactional
    @PostMapping(value = "/userInfo2")
    public Response userInfo2(@RequestBody LoginDto login) {
        Optional<User> user = userService.findByLogin(login.getLogin());
        if (user.isPresent()){
            UserProfile up = user.get().getUserProfile();
            if (up == null) {
                return new Response(false, 2, "user has not profile, please send data");
            }
            String photoUrl = up.getPhotoUrl().replaceAll("\\\\","\\");
            return new Response(true,0,
                    new UserResponse(up.getUserName(), photoUrl, up.getContact(TypeContact.PHONE_NUMBER).getContact()));
        }
        return new Response(false,1, "no such user");
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

    @PostMapping(value = "/pendingWinnings")
    public Response getPendingWinnings(@RequestBody LoginDto dto){
    //    return new Response(true, 0, new PendingWinningsResponse(userService.hasPendingWinnings(dto.getLogin())));
        return new Response(true, 0, new PendingWinningsResponse(true));
    }

    @PostMapping(value = "/unreadNotifications")
    public Response getUnreadNotifications(@RequestBody LoginDto dto){
    //    return new Response(true, 0, new UnreadNotificationsResponse(userService.hasUnreadNotifications(dto.getLogin())));
        return new Response(true, 0, new UnreadNotificationsResponse(true));
    }

    @PostMapping(value = "/winnings")
    public Response getAllWinnings(@RequestBody LoginDto dto){
        Optional<User> user = userService.findByLogin(dto.getLogin());
        if (user.isPresent()){
            Optional<List<Winning>> winnings = userService.getWinnings(user.get());
            if (winnings.isPresent()){
                return new Response(true,0, new WinningsResponse(winningsConverter.convertAllWinnings(winnings.get())));
            }else {
                return new Response(false, 1, "there are no notifications");
            }
        }
        return new Response(false, 2, "no such user");
    }

    @PostMapping(value = "/notifications")
    public Response getAllNotifications(@RequestBody LoginDto dto){
        Optional<User> user = userService.findByLogin(dto.getLogin());
        if (user.isPresent()){
            Optional<List<Notification>> notifications = userService.getNotifications(user.get());
            if (notifications.isPresent()){
                return new Response(true,0, new NotificationResponse(notificationsConverter.convertAllNotifications(notifications.get())));
            }else {
                return new Response(false, 1, "there are no winnings");
            }
        }
        return new Response(false, 2, "no such user");

    }

    @PostMapping(value = "/shares")
    public Response getSharesByLocation(@RequestBody SharesRequestDto dto){

        Optional<User> user = userService.findByLogin(dto.getLogin());

        List<Share> regionShares = shareService.findAllByRegionCode(shareService.getRegionCode(
                dto.getCountry(), dto.getRegion(), dto.getCity()));

        if (user.isPresent() && regionShares != null){
            Optional<List<ShareCellType>> shareCellTypes = userService.getShareCellTypes(user.get(), regionShares);
            if (shareCellTypes.isPresent()) {
                return new Response(true, 0, shareConverter.cellTypesResponse(regionShares, shareCellTypes.get(), dto.getCoordinates()));
            }
        }
        return new Response(false, 2, "no such user");

    }
}

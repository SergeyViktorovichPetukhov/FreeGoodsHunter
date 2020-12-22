package com.sergo.wic.controller;

import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.UserProfileResponse;
import com.sergo.wic.dto.UserNameInfoDto;
import com.sergo.wic.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private UserFacade userFacade;

    public UserProfileController(@Autowired UserFacade userFacade){
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response getUserProfile(@RequestBody LoginDto dto) {
        return new Response(true, 0, userFacade.getUserProfile(dto.getLogin() , dto.isRequestedFromMenu()));
    }

    @PostMapping(value = "/addUserNameInfo")
    public Response addUserNameInfo(@RequestBody UserNameInfoDto dto) {
        boolean result = userFacade.addUserNameInfo(dto.getLogin(),dto.getFirstNameAndLastName());
        if (result){
            return new Response(true, 0, "first name and last name added");
        }else return new Response(false,1,"no such user");
    }
}

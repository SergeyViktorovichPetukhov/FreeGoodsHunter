package com.sergo.wic.controller;

import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.UserProfileResponse;
import com.sergo.wic.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserProfileController {

    private UserFacade userFacade;

    public UserProfileController(@Autowired UserFacade userFacade){
        this.userFacade = userFacade;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Response getUserProfile(@RequestParam(value = "login") String login,
                                   @RequestParam(value = "isRequestedFromMenu") boolean isRequestedFromMenu) {
        return new Response(true,0,userFacade.getUserProfile(login , isRequestedFromMenu));
    }
}

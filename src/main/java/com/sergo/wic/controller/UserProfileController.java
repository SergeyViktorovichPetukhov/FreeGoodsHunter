package com.sergo.wic.controller;

import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.ImageUrlResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.UserProfileResponse;
import com.sergo.wic.dto.UserNameInfoDto;
import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.image.ImageAccessException;

import java.awt.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private UserFacade userFacade;
    private ImageService imageService;

    public UserProfileController(@Autowired UserFacade userFacade,
                                 @Autowired ImageService imageService){
        this.userFacade = userFacade;
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response getUserProfile(@RequestBody LoginDto dto) {
        return new Response(true, 0, userFacade.getUserProfile(dto.getLogin() , dto.isRequestedFromMenu()));
    }

    @PostMapping(value = "/addUserNameInfo")
    public Response addUserNameInfo(@RequestBody UserNameInfoDto dto) {
        if (userFacade.addUserNameInfo(dto.getLogin(),dto.getFirstNameAndLastName())){
            return new Response(true, 0, "first name and last name added");
        }else return new Response(false,1,"no such user");
    }

    @PostMapping(value = "/uploadPhoto", consumes = MediaType.MULTIPART_MIXED_VALUE)
    public Response uploadPhoto(@RequestPart MultipartFile file,
                                @RequestParam String login) throws ImageNotUploadedException {
        return new Response(true, 0,
                new ImageUrlResponse(imageService.saveUserPhoto(file,login)));

    }
}

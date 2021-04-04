package com.sergo.wic.controller;

import com.sergo.wic.dto.AddUserProfileDto;
import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.ImageUrlResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.UserProfileResponse;
import com.sergo.wic.dto.UserNameInfoDto;
import com.sergo.wic.entities.Contact;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserProfile;
import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.service.ImageService;
import com.sergo.wic.service.UserProfileService;
import com.sergo.wic.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.image.ImageAccessException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    private UserFacade userFacade;
    private ImageService imageService;
    private UserService userService;
    private UserProfileService userProfileService;

    public UserProfileController(@Autowired UserFacade userFacade,
                                 @Autowired ImageService imageService,
                                 @Autowired UserService userService,
                                 @Autowired UserProfileService userProfileService){
        this.userFacade = userFacade;
        this.imageService = imageService;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response getUserProfile(@RequestBody LoginDto dto) {
        return new Response(true, 0, userFacade.getUserProfile(dto.getLogin() , dto.isRequestedFromMenu()));
    }

    @Transactional
    @PostMapping("/add")
    public Response addUserProfile(@RequestBody AddUserProfileDto dto) {
        Optional<User> userOptional = userService.findByLogin(dto.getLogin());
        if (!userOptional.isPresent()) {
            userService.save(convertToUserProfile(dto));
            return new Response(false, 1 ,"user profile added");
        }
        return new Response(false, 1 ,"user already exists");
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

    private User convertToUserProfile(AddUserProfileDto dto) {
        User user = new User();
        user.setLogin(dto.getLogin());
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(dto.getUserName());
        userProfile.setRange(dto.getRange());
        userProfile.setCollection_area(dto.getCollectionArea());
        userProfile.setRegion(dto.getRegion());
        List<Contact> userContacts = new ArrayList<>();
        IntStream.range(0, dto.getContacts().size()).forEach(i -> {
            Contact contact = new Contact(dto.getContacts().get(i).getTypeContact(), dto.getContacts().get(i).getContact());
            contact.setUserProfile(userProfile);
            userContacts.add(contact);
        });
        userProfile.setContacts(userContacts);
        user.setUserProfile(userProfile);
        return user;
    }
}

package com.sergo.wic.controller;

import com.sergo.wic.dto.Response.*;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.ShareFacade;
import com.sergo.wic.facade.UserFacade;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class MockController {

    @Autowired
    private ShareFacade shareFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CompanyFacade companyFacade;

    @Autowired
    private ServletContext servletContext;



    @GetMapping("/images/uploadedPhotos/{id}")
    public void getImageAsResource(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
//        return new ServletContextResource(servletContext, servletContext.getContextPath() + "src/main/uploadedPhotos/" + id);

        String path = servletContext.getContextPath() + "/src/main/uploadedPhotos/" + id;
        InputStream in = servletContext.getResourceAsStream(path);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

//    @GetMapping("/shares")
//    public SharesResponse getShares(@RequestParam(value = "login", required = false) String login,
//                                    @RequestParam(value = "country", required = false) String country,
//                                    @RequestParam(value = "region", required = false) String region,
//                                    @RequestParam(value = "city", required = false) String city) {
//        return shareFacade.getShares(login, country, region, city);
//    }

    @GetMapping("/profile")
    public UserProfileResponse getUserProfile(@RequestParam(value = "login", required = false) String login) {
        return userFacade.getUserProfile(login);
    }

//    @GetMapping("/company")
//    public CompanyResponse getCompany(@RequestParam(value = "login", required = false) String login) {
//        return companyFacade.getShares(login);
//    }

    @GetMapping("/allItems")
    public AllItemsResponse getAllItems(@RequestParam(value = "login", required = false) String login) {
        return userFacade.getAllItems(login);
    }

    @GetMapping("/notices")
    public NoticeResponse getNotices(@RequestParam(value = "login", required = false) String login) {
        return userFacade.getNotices(login);
    }

    @PostMapping("/profile/addContact")
    public Response addContactToUser(@RequestParam(value = "login", required = true) String login,
                                     @RequestParam(value = "typeContact", required = true) String
                                             typeContact,
                                     @RequestParam(value = "contact", required = true) String contact) {
        return userFacade.addContactToUser(login, typeContact, contact);
    }

    @PostMapping("/profile/setPhoto")
    public Response setPhotoToUser(@RequestParam(value = "login", required = true) String login,
                                   @RequestParam(value = "photo", required = true) MultipartFile photo) {
        return userFacade.setPhotoToUser(login, photo);
    }

    @GetMapping("/friends")
    public FriendListResponce getFriends(@RequestParam(value = "login", required = false) String login) {
        return userFacade.getFriends(login);
    }

    @GetMapping("/rating")
    public RatingResponse getRatingOfUsers(@RequestParam(value = "country", required = false) String country,
                                           @RequestParam(value = "region", required = false) String region,
                                           @RequestParam(value = "city", required = false) String city) {
        return userFacade.getRatingOfUsers(country, region, city);
    }
}

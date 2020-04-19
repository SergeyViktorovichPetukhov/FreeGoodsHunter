package com.sergo.wic.controller;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.Response;
import com.sergo.wic.dto.ShareResponse;
import com.sergo.wic.dto.entity.CreateShareDto;
import com.sergo.wic.entities.Share;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.facade.ShareFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.ContentType;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareFacade shareFacade;

    @Autowired
    private ShareConverter shareConverter;

    @Autowired
    private ImageFacade imageFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/publish" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShareResponse publicShare( @Valid @RequestBody final CreateShareDto createShareDto
                                     ,@RequestParam("productPhoto") final byte[] productPhoto) {
        createShareDto.setProductPhoto(productPhoto);
        return new ShareResponse(shareConverter
                                    .convertToModel(shareFacade.saveShare(createShareDto))
                                         .getShareId());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/cancel")
    public Response cancelShare(@RequestParam ("login") String login, @RequestParam("share_id") String shareId ) {
        return shareFacade.deleteShare(shareId);
    }
}



//        System.out.println(createShareDto.getLogin());
//                System.out.println(createShareDto.getProductName());
//                System.out.println(createShareDto.getDescription());
//                System.out.println(createShareDto.getLinkOnProductUrl());
//                System.out.println(createShareDto.getProductPrice());
//                System.out.println(createShareDto.getProductCount());
//                System.out.println(createShareDto.getAnnouncementDuration());
//                System.out.println(createShareDto.getShareDuration());
//                System.out.println(createShareDto.getAfterShareDuration());
//                System.out.println(createShareDto.getPlaceCountry());
//                System.out.println(createShareDto.getPlaceRegion());
//                System.out.println(createShareDto.getPlaceCity());
//                System.out.println(createShareDto.getItems().get(0).getLat() + " " + createShareDto.getItems().get(0).getLon());
//                System.out.println(createShareDto.getColor());

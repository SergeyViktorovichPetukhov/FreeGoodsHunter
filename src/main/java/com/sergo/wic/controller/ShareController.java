package com.sergo.wic.controller;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.LoginAndShareDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.ShareResponse;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.facade.ShareFacade;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareFacade shareFacade;

    @Autowired
    private ShareConverter shareConverter;

    @Autowired
    ShareService shareService;

    @Autowired
    private ImageFacade imageFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/publish" ,consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShareResponse publicShare( @Valid @RequestBody final CreateShareDto createShareDto
                                     ,@RequestPart("productPhoto") MultipartFile productPhoto) {
        createShareDto.setPhotoProduct(productPhoto);
        return new ShareResponse(shareConverter
                                    .convertToModel(shareFacade.saveShare(createShareDto))
                                         .getShareId());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response cancelShare(@RequestBody LoginAndShareDto dto) {
        if (shareService.checkLoginAndShare(dto.getLogin(),dto.getShareId()))
            return shareFacade.deleteShare(dto.getShareId());
        else return new Response(false,1,"you are not the owner");
    }
}



//        System.out.println(createShareDto.getLogin());
//                System.out.println(createShareDto.getProductName());
//                System.out.println(createShareDto.getProductDescription());
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

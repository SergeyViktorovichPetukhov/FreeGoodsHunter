package com.sergo.wic.controller;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.LoginAndShareDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.ShareResponse;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.entities.Share;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.facade.ShareFacade;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.validation.ShareValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Autowired
    private ShareFacade shareFacade;

    @Autowired
    private ShareConverter shareConverter;

    @Autowired
    private ShareValidator shareValidator;

    @Autowired
    ShareService shareService;

    @Autowired
    private ImageFacade imageFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/publish" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                                      produces = MediaType.APPLICATION_JSON_VALUE)
                                   // @Valid
    public ShareResponse publicShare( @RequestPart(value = "сreateShareDto", required = false) final CreateShareDto createShareDto
                                     ,@RequestPart(value = "productPhoto", required = false) MultipartFile productPhoto) {

        String shareId;
        System.out.println("gfkgyukik");
        try{
           shareId = shareService.saveShare(
                        shareConverter.convertToModel(createShareDto), productPhoto).getShareId();
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
            return new ShareResponse(false,e.getMessage());
        }
        return new ShareResponse(shareId);
    }

//@ResponseStatus(HttpStatus.CREATED)
//@PostMapping(value = "/publish" , consumes = MediaType.APPLICATION_JSON_VALUE,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//
//public ShareResponse publicShare( @RequestBody final CreateShareDto createShareDto) {
//
//    String shareId;
//    try{
//        shareId = shareService.saveShare1(
//                shareConverter.convertToModel(createShareDto)).getShareId();
//    }catch (IOException | NullPointerException e){
//        e.printStackTrace();
//        return new ShareResponse(false,e.getMessage());
//    }
//    return new ShareResponse(shareId);
//}

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response cancelShare(@RequestBody LoginAndShareDto dto) {
        if (shareService.checkShare(dto.getShareId()))
            return shareFacade.deleteShare(dto.getShareId());
        else return new Response(false,1,"share does not exists");
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
//                System.out.println(createShareDto.getItems().get(0).getLatitude() + " " + createShareDto.getItems().get(0).getLongitude());
//                System.out.println(createShareDto.getColor());
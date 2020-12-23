package com.sergo.wic.controller;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.LoginAndShareDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.ShareInfoResponse;
import com.sergo.wic.dto.Response.ShareResponse;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.facade.ShareFacade;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/share")
public class ShareController {

    private ShareFacade shareFacade;

    private ShareConverter shareConverter;

    private ShareService shareService;

    private ImageFacade imageFacade;

    public ShareController(@Autowired ShareFacade shareFacade,
                           @Autowired ShareConverter shareConverter,
                           @Autowired ShareService shareService,
                           @Autowired ImageFacade imageFacade){
        this.shareFacade = shareFacade;
        this.shareService = shareService;
        this.shareConverter = shareConverter;
        this.imageFacade = imageFacade;
    }

    @GetMapping("/{shareId}")
    public Response shareInfo(@RequestParam(value = "login", required = false) String login,
                              @PathVariable("shareId") String shareId){
        Optional<Share> share = shareService.findShareWithCompany(shareId);
        if (share.isPresent()){
            return new Response(true, 0, shareConverter.convertToShareInfoResponse(share.get()));
        }
        return new Response(false, 1,"no such share");
    }

    @GetMapping("/itemsInfo/{shareId}")
    public Response shareItemsInfo(@RequestParam(value = "login", required = false) String login,
                                   @PathVariable("shareId") String shareId){
        List<Item> items = shareService.findShareUserItems(shareId);
        if (items != null){
            return new Response(true, 0, shareConverter.convertToShareItemsResponse(items));
        }
        return new Response(false, 1,"no such share");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/publish" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                                      produces = MediaType.APPLICATION_JSON_VALUE)
    public Response publishShare(@RequestPart(value = "сreateShareDto")CreateShareDto createShareDto
                                ,@RequestPart(value = "productPhoto") MultipartFile productPhoto) {

        String shareId;
        try{
           shareId = shareService.saveShare(
                     shareConverter.convertToModel(createShareDto), productPhoto)
                     .getShareId();
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
            return new Response(false, 1,e.getMessage());
        }
        return new Response(true,0,"share published", new ShareResponse(shareId));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response cancelShare(@RequestBody LoginAndShareDto dto) {
        if (shareService.checkShare(dto.getShareId()))
            return shareFacade.deleteShare(dto.getShareId());
        else return new Response(false,1,"share does not exists");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/productPhoto/{shareId}")
    public Response setPhotoForShareProduct(@RequestParam(value = "photo") MultipartFile photo,
                                            @PathVariable String shareId) {
        shareFacade.uploadPhotoForShareProduct(photo, shareId);
        return new Response(true,0,"photo uploaded");
    }


}


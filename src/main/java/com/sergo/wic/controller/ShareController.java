package com.sergo.wic.controller;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.AddItemsDto;
import com.sergo.wic.dto.LoginAndShareDto;
import com.sergo.wic.dto.Response.*;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.dto.SharesRequestDto;
import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.facade.ShareFacade;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.ImageService;
import com.sergo.wic.service.ItemService;
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

    private ImageService imageService;

    private CompanyService companyService;

    private ItemService itemService;

    public ShareController(@Autowired ShareFacade shareFacade,
                           @Autowired ShareConverter shareConverter,
                           @Autowired ShareService shareService,
                           @Autowired ImageService imageService,
                           @Autowired CompanyService companyService,
                           @Autowired ItemService itemService){
        this.shareFacade = shareFacade;
        this.shareService = shareService;
        this.shareConverter = shareConverter;
        this.imageService = imageService;
        this.companyService = companyService;
        this.itemService = itemService;
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
    public Response publishShare(@RequestPart CreateShareDto dto
                                ,@RequestPart MultipartFile productPhoto) {
        Optional<Company> company = companyService.findByLogin(dto.getLogin());
        if (company.isPresent()){
            String shareId;
            try{
                Share share = shareConverter.convertToModel(dto, company.get());
                shareId = shareService.saveShare(share, productPhoto).getShareId();
            }catch (IOException | NullPointerException e){
                e.printStackTrace();
                return new Response(false, 1,e.getMessage());
            }
            return new Response(true,0,"share published", new ShareResponse(shareId));
        }
        return new Response(false,1,"no such company");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response cancelShare(@RequestBody LoginAndShareDto dto) {
        if (shareService.checkShare(dto.getShareId()))
            return shareFacade.deleteShare(dto.getShareId());
        else return new Response(false,1,"share does not exists");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/productPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response setPhotoForShareProduct(@RequestPart MultipartFile photo,
                                            @RequestPart UploadImageDto dto) throws ImageNotUploadedException {
        return new Response(true,0,"photo uploaded",
                new ImageUrlResponse(imageService.saveProductPhoto(photo, dto.getProductName(), dto.getCompanyName()))
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addItems")
    public Response addItems(@RequestBody AddItemsDto dto) {
        itemService.addNewShareItems(dto.getItems(), dto.getShareId());
        return new Response(true,0,"items added tos share");
    }

    @PostMapping(value = "/allItems")
    public Response allItems(@RequestBody SharesRequestDto dto) {
        return new Response(true, 0 ,
                shareConverter.shareItemsResponse(shareService.findAllByRegionCode(
                        shareService.getRegionCode(dto.getCountry(), dto.getRegion(), dto.getCity()))));

    }

    @GetMapping(value = "/items")
    public Response items(@RequestParam("login") String login, @RequestParam("shareId") String shareId) {
        Optional<Share> share = shareService.findByShareId(shareId);
        if (share.isPresent()) {
        return new Response(true, 0 ,
                shareConverter.shareItemsCountResponse(share.get()));
        }
        return new Response(false, 1, "no such share");
    }

}


package com.sergo.wic.controller;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.Response.GenericResponse;
import com.sergo.wic.dto.Response.Response;

import com.sergo.wic.dto.UploadShareDto;
import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/develop")
public class DevelopController {

    @Autowired
    ShareService shareService;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    ShareConverter shareConverter;


    @GetMapping(value = "/getShare")
    public Response getShare(@RequestParam String shareId) {
        Optional<Share> shareOptional = shareService.findByShareId(shareId);
        if (shareOptional.isPresent()) {
            return new Response(true, 0, shareConverter.convertToShareResponse(shareOptional.get()));
        }
        return new Response(false, 1, "no share with such shareId");
    }

    @GetMapping(value = "/getAllShares")
    public Response getAllShares() {
        return new Response(true, 0, shareConverter.convertToAllShareResponse(shareService.findAll()));
    }

    @PostMapping(value = "/uploadShare")
    public Response uploadShare(@RequestBody UploadShareDto dto) {
        shareService.saveShare(shareConverter.convertToModel(dto));
        return null;
    }

}

package com.sergo.wic.controller;

import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    private ShareService shareService;

    public AjaxController(@Autowired ShareService shareService){
        this.shareService = shareService;
    }

    @GetMapping(value = "/getNewShares", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Share> getShares(
            @RequestParam(value = "max_share_id") String maxShareId,
            @RequestParam("company_id") String companyId
            ){
        return shareService.findNewShares(Long.valueOf(maxShareId),Long.valueOf(companyId));
    }
}

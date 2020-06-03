package com.sergo.wic.controller;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ShareService;
import javafx.util.converter.TimeStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;
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
        System.out.println("maxShareId : " + maxShareId);
        return shareService.findNewShares(Long.valueOf(maxShareId),Long.valueOf(companyId));
    }
}
package com.sergo.wic.controller;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.Response.CompanyResponse;
import com.sergo.wic.dto.Response.GetShareResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.RequestGetShareDto;
import com.sergo.wic.dto.LoginDto;
import com.sergo.wic.dto.Response.ShareResponse;
import com.sergo.wic.entities.Share;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyFacade companyFacade;

    @Autowired
    UserFacade userFacade;

    @Autowired
    ShareService shareService;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private ShareConverter shareConverter;

    @PostMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response companyResponse(@RequestBody LoginDto dto){
        if (userFacade.hasUserCompany(dto.getLogin())) {
            CompanyResponse companyResponse = companyFacade.getShares(dto.getLogin());
            return new Response(true,0,companyResponse);
        }
        return new Response(false, 1,"user doesn't have company");
    }

    @PostMapping(value = "/getShare")
    @ResponseStatus(value = HttpStatus.OK)
    public Response getShare(@RequestBody RequestGetShareDto dto){
        System.out.println(dto.getLogin() + " " + dto.getShareId());
        Optional<Share> share = shareService.findByShareId(dto.getShareId());
        if (share.isPresent() & userFacade.isLoginValid(dto.getLogin())){
            GetShareResponse getShareResponse = shareConverter.convertToShareResponse(share.get());
            return new Response(true,0,getShareResponse);
        }
        return new Response(false,1,"no such share");
    }

    @PostMapping(value = "/addLable", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addLabel(@RequestParam (value = "login") String login,
                             @RequestBody byte[] byteArray){
        return companyFacade.addLabel(login,byteArray);
    }
}

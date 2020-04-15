package com.sergo.wic.controller;

import com.sergo.wic.dto.Response;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyFacade companyFacade;

    @Autowired
    UserFacade userFacade;

    @GetMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response companyResponse(@RequestParam (value = "login") String login){
        if (userFacade.hasUserCompany(login)) {
            return companyFacade.getShares(login);
        }
        return new Response(false, 1,"user doesn't have company");
    }

    @PostMapping(value = "/addLable", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addLabel(@RequestParam (value = "login") String login,
                             @RequestBody byte[] byteArray){
        return companyFacade.addLabel(login,byteArray);
    }
}

package com.sergo.wic.controller;

import com.sergo.wic.dto.Response;
import com.sergo.wic.dto.VerifyCodeDto;
import com.sergo.wic.entities.User;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyFacade companyFacade;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping(value = "/registrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response generateCode(@RequestParam ("login") String login,
                                 @RequestParam ("address") String address,
                                 @RequestParam ("phone") String phone,
                                 @RequestParam ("code") String code,
                                 @RequestParam (value = "internet_address",required = false) String internet_shop) {
        User user;
        try{
            user = userService.findByLogin(login);
        }catch (RuntimeException e){
            return new Response(false,0,"no such user");
        }

        if (internet_shop != null)
            return userFacade.registerCompany(login, address, phone, internet_shop, user.getId(), Integer.valueOf(code));
        else
            return userFacade.registerCompany(login, address, phone,null, user.getId(),Integer.valueOf(code));
    }

    @PostMapping("/verifyCode")
    @ResponseStatus(HttpStatus.OK)
    public Response verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        return userFacade.verifyCode(verifyCodeDto.getPhone(), verifyCodeDto.getCode());
    }
}

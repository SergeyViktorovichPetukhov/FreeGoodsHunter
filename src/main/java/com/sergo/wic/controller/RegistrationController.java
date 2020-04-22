package com.sergo.wic.controller;

import com.sergo.wic.dto.RegistrationDto;
import com.sergo.wic.dto.Response.Response;
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

    @PostMapping(value = "/registrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response generateCode(@RequestBody RegistrationDto registrationDto) {
        try {
            User user = userService.findByLogin(registrationDto.getLogin())
                                   .orElseThrow(() -> new RuntimeException("no such user"));
        }catch (RuntimeException e){
            return new Response(false,0,"no such user");
        }
        return userFacade.registerCompany(registrationDto.getLogin(), registrationDto.getPhone());
    }

    @PostMapping(value = "/verifyCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        return userFacade.verifyCode(verifyCodeDto.getLogin(),
                                     verifyCodeDto.getCode(),
                                     verifyCodeDto.getAddress(),
                                     verifyCodeDto.getPhone());
    }
}

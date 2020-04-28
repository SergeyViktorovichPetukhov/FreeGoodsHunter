package com.sergo.wic.controller;

import com.google.maps.GeoApiContext;
import com.sergo.wic.dto.RegistrationDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.VerifyCodeDto;
import com.sergo.wic.entities.User;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.google_api.GooglePlacesRequestor;
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

    @Autowired
    private GooglePlacesRequestor requestor;

    @PostMapping(value = "/registrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response generateCode(@RequestBody RegistrationDto dto) {
//        try {
//            User user = userService.findByLogin(dto.getLogin())
//                                   .orElseThrow(() -> new RuntimeException("no such user"));
//        }catch (RuntimeException e){
//            return new Response(false,0,"no such user");
//        }

        if (requestor.checkPlaceByPhoneAndPlaceName(dto.getPhone(),dto.getPlaceName()))
           return userFacade.registerCompany(dto.getLogin(), dto.getPhone());
        else return new Response(false,2,"no");
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

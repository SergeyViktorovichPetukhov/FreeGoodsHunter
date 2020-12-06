package com.sergo.wic.controller;

import com.sergo.wic.company_check.WebSiteChecker;
import com.sergo.wic.dto.GoogleRegistrationDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.VerifyCodeDto;
import com.sergo.wic.dto.WebRegistrationDto;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.company_check.GooglePlacesRequestor;
import com.sergo.wic.service.email.EmailService;
import com.sergo.wic.utils.Constants;
import com.sergo.wic.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private GooglePlacesRequestor googlePlacesRequestor;

    @Autowired
    private WebSiteChecker webSiteChecker;

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/googleRegistrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response registerByGooglePlaces(@RequestBody GoogleRegistrationDto dto) {
        if (googlePlacesRequestor.checkPlaceByPhoneAndPlaceName(dto.getPhone(),dto.getPlaceID(),dto.getCountryCode()))
           return userFacade.registerByGooglePlaces(dto.getLogin(), dto.getPhone());
        else return new Response(false,1,"company not found");
    }

    @PostMapping(value = "/webRegistrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response registerByWebSite(@RequestBody WebRegistrationDto dto) {

        if (dto.getUserContact().matches(Constants.PHONE_REGEX)) {
            boolean isChecked = webSiteChecker.checkHtmlPageByPhone(dto.getUrl(),dto.getUserContact());
            // smsSender.sendSms()
            return userFacade.registerByWebSite(dto.getLogin(), dto.getUserContact(),isChecked, dto.getUrl());
        }

        if (EmailValidator.isValid(dto.getUserContact().toCharArray())) {
            boolean isChecked = webSiteChecker.checkHtmlPageByEmail(dto.getUrl(),dto.getUserContact());
            return userFacade.registerByWebSite(dto.getLogin(),dto.getUserContact(),isChecked, dto.getUrl());
        }

        else return new Response(false,2,Constants.VERIFICATION_UNSUCCESS);
    }

    @PostMapping(value = "/verifyCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        return userFacade.verifyCode(verifyCodeDto.getLogin(),
                                     verifyCodeDto.getRegId(),
                                     verifyCodeDto.getCode());
    }


    @ExceptionHandler({MalformedURLException.class , IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleException(Exception ex)
    {
        ex.printStackTrace();
        return new Response(false,1,ex.getMessage());
    }
}

package com.sergo.wic.controller;

import com.sergo.wic.company_check.AWSAPIChecker;
import com.sergo.wic.company_check.WebSiteChecker;
import com.sergo.wic.dto.GoogleRegistrationDto;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.VerifyCodeDto;
import com.sergo.wic.dto.WebRegistrationDto;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.company_check.GooglePlacesRequestor;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserService;
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
    private UserService userService;

    @Autowired
    private CompanyFacade companyFacade;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private GooglePlacesRequestor googlePlacesRequestor;

    @Autowired
    private WebSiteChecker webSiteChecker;

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/googleRegistrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response registerByGooglePlaces(@RequestBody GoogleRegistrationDto dto) {
//        try {
//            User user = userService.findByLogin(dto.getLogin())
//                                   .orElseThrow(() -> new RuntimeException("no such user"));
//        }catch (RuntimeException e){
//            return new Response(false,0,"no such user");
//        }

        if (googlePlacesRequestor.checkPlaceByPhoneAndPlaceName(dto.getPhone(),dto.getPlaceName()))
           return userFacade.registerCompany(dto.getLogin(), dto.getPhone());
        else return new Response(false,2,"no");
    }

    @PostMapping(value = "/webRegistrationCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response registerByWebSite(@RequestBody WebRegistrationDto dto) {

        if (dto.getUserContact().matches(Constants.PHONE_REGEX))
        {
            if (webSiteChecker.checkHtmlPageByPhone(dto.getUrl(),dto.getUserContact()))
            // тут надо посмотреть как регистрировать компанию по имейл
            {
                // smsSender.sendSms()
                return userFacade.registerCompany(dto.getLogin(), dto.getUserContact(), dto.getUrl());
            }
            else return new Response(false,2,"we dont see phone on your website");
        }
        if (EmailValidator.isValid(dto.getUserContact().toCharArray())
         && webSiteChecker.checkHtmlPageByEmail(dto.getUrl(),dto.getUserContact()))
        {
            emailService.sendSimpleMessage(dto.getUserContact(),"web-site verification",Constants.WEB_SITE_VERIFICATION_MESSAGE);
            return userFacade.registerCompany(dto.getLogin(),dto.getUserContact());
        }

        else return new Response(false,2,Constants.VERIFICATION_UNSUCCESS);
    }

    @PostMapping(value = "/verifyCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Response verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        return userFacade.verifyCode(verifyCodeDto.getLogin(),
                                     verifyCodeDto.getCode(),
                                     verifyCodeDto.getAddress(),
                                     verifyCodeDto.getPhone());
    }

    @ExceptionHandler({MalformedURLException.class , IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleException(Exception ex)
    {
        ex.printStackTrace();
        return new Response(false,1,ex.getMessage());
    }
}

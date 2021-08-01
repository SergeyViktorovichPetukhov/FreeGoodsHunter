package com.sergo.wic.facade.impl;

import com.sergo.wic.dto.AddressDto;
import com.sergo.wic.dto.Response.CompanyResponse;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.ShareForCompanyDto;
import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.enums.ShareState;
import com.sergo.wic.facade.CompanyFacade;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.repository.CompanyRepository;
import com.sergo.wic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;

@Component
public class CompanyFacadeImpl implements CompanyFacade {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyResponse getShares(final String login) {
        return null;
                //getUserProfileTestData(userFacade.isLoginValid(login));
    }




//    private CompanyResponse getUserProfileTestData(final Boolean valid) {
//        CompanyResponse userProfileResponse = new CompanyResponse();
//        if (valid) {
//            ShareForCompanyDto cocaColaShare = new ShareForCompanyDto("cocacola_0_1", new Date(),"/images/cocacola-logo.png",  23,
//                    0, ShareState.ACTIVE.ordinal());
//            ShareForCompanyDto cocaColaShare2 = new ShareForCompanyDto("cocacola_0_2", new Date(),"/images/cocacola-logo.png",  44,
//                    0, ShareState.FINISHED.ordinal());
//
//            AddressDto address1 = new AddressDto("Ukraine", "Kiev reg", "Kiev", "Shevchenka 20");
//            AddressDto address2 = new AddressDto("Ukraine", "Kiev reg", "Kiev", "Lenina 3/5, room 5");
//
//            //userProfileResponse.setShares(Arrays.asList(cocaColaShare, cocaColaShare2));
//            userProfileResponse.setContacts(userFacade.getUserContacts().get("user@gmail.com"));
//            userProfileResponse.setLabel("/photoFGH/company owner 00000000-0000-03e8-0000-00000000001a.jpg");
//            userProfileResponse.setNameCompany("Macdonalds");
//            userProfileResponse.setShortDescription("Fast food");
//            userProfileResponse.setWebPage("https://mcdonalds.by/ru/");
//            userProfileResponse.setShops(Arrays.asList(address1, address2));
//
////            userProfileResponse.setSuccess(true);
////            userProfileResponse.setErrorCode(0);
//        } else {
////            userProfileResponse.setSuccess(false);
////            userProfileResponse.setErrorCode(1);
//        }
//        return userProfileResponse;
//    }

    @Override
    public Response addLabel(@NotNull String login, @NotNull byte[] byteArray) {
        // have to add check
        Company company = companyRepository.findByLogin(login).get();
    //    company.setLabel(byteArray);
        companyRepository.save(company);
        return new Response(true,0);
    }

    @Override
    public Response changeName(String login, String newCompanyName) {
        return null;
    }

    @Override
    public Response addShortDescription(String login, String shortDesc) {
        return null;
    }

    @Override
    public Response addContact(String login, String typeContact, String contact) {
        return null;
    }

    @Override
    public Response removeContact(String login, String typeContact, String contact) {
        return null;
    }

    @Override
    public Response addWebPage(String login, String webPage) {
        return null;
    }

    @Override
    public Response addAddress(String login, String country, String region, String city, String address) {
        return null;
    }

    @Override
    public Response removeAddress(String login, String country, String region, String city, String address) {
        return null;
    }
}

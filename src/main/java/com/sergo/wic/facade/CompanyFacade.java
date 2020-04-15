package com.sergo.wic.facade;

import com.sergo.wic.dto.CompanyResponse;
import com.sergo.wic.dto.Response;

public interface CompanyFacade {
    CompanyResponse getShares(String login);
    Response addLabel(String login, byte[] byteArray);
    Response changeName(String login, String newCompanyName);
    Response addShortDescription(String login, String shortDesc);
    Response addContact(String login, String typeContact, String contact);
    Response removeContact(String login, String typeContact, String contact);
    Response addWebPage(String login, String webPage);
    Response addAddress(String login, String country, String region, String city, String address);
    Response removeAddress(String login, String country, String region, String city, String address);
}

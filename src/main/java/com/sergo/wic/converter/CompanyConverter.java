package com.sergo.wic.converter;

import com.sergo.wic.dto.AddressDto;
import com.sergo.wic.dto.ContactDto;
import com.sergo.wic.dto.Response.CompanyResponse;
import com.sergo.wic.dto.ShareDto;
import com.sergo.wic.entities.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyConverter {

    @Autowired
    ModelMapper modelMapper;

    public CompanyResponse companyResponse(Company company) {
        List<ContactDto> contacts = company.getContacts().stream()
                .map(contact -> {
                   return new ContactDto(contact.getTypeContact(), contact.getContact());
                })
                .collect(Collectors.toList());
        List<AddressDto> addresses = company.getAddresses().stream()
                .map(address -> {
                    return new AddressDto(address.getCountry() ,address.getRegion(), address.getCity(), address.getAddressLine());
                })
                .collect(Collectors.toList());
        List<ShareDto> shares = company.getShares().stream()
                .map(share -> {
                    ShareDto shareDto = new ShareDto();
                    shareDto.setShareId(share.getShareId());
                    shareDto.setPhotoProductUrl(share.getProductPhotoUrl());
                    shareDto.setCellType(share.getStatus());
                    shareDto.setProductName(share.getProductName());
                    shareDto.setDate(share.getDate());
                    shareDto.setProductPrice(share.getProductPrice());
                    shareDto.setPickedItemsCount(share.getPickedItemsCount());
                    shareDto.setAllItemsCount(share.getAllItemsCount());
                    return shareDto;

                }).collect(Collectors.toList());
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setLabel(company.getLogoUrl());
        companyResponse.setNameCompany(company.getName());
        companyResponse.setShortDescription(company.getInfo());
        companyResponse.setIsCompanyVerificated(company.getIsVerificated());
        companyResponse.setContacts(contacts);
        companyResponse.setShares(shares);
        companyResponse.setShops(addresses);
        return companyResponse;
    }
}

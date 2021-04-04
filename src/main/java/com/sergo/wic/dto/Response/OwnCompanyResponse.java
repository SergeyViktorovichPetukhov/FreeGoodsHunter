package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.AddressDto;
import com.sergo.wic.dto.CompanyShareDto;
import com.sergo.wic.dto.ContactDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OwnCompanyResponse extends ResponseContent {
    private String label;
    private String nameCompany;
    private String shortDescription;
    private Boolean isVerificated;
    private List<ContactDto> contacts;
    private String webPage;
    private List<AddressDto> shops;
    private List<CompanyShareDto> shares;
}

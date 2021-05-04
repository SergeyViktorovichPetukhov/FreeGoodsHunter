package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResponse extends ResponseContent {
    private String label;
    private String nameCompany;
    private String shortDescription;
    private List<ContactDto> contacts;
    private Boolean isCompanyVerificated;
//    private TypeContact typeContact;
//    private ContactDto contact;
    private String webPage;
//    private String country;
//    private String region;
//    private String city;
//    // ??? AddressDto address
//    private String address;
    //???
    private List<AddressDto> shops;
    private List<ShareDto> shares;
//    private String shareDates;
//    private ShareState state;
//    private String date;
//    private String productLableUrl;
//    private String shareId;
//    private Integer pickedItemsCount;
//    private Integer allItemsCount;

}

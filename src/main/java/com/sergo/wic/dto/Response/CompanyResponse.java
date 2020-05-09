package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.*;
import com.sergo.wic.entities.ShareState;

import java.util.List;

public class CompanyResponse extends ResponseContent {
    private String label;
    private String nameCompany;
    private String shortDescription;
    private List<ContactDto> contacts;
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
    private List<ShareForCompanyDto> shares;
//    private String shareDates;
//    private ShareState state;
//    private String date;
//    private String productLableUrl;
//    private String shareId;
//    private Integer pickedItemsCount;
//    private Integer allItemsCount;

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(final String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(final String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(final List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(final String webPage) {
        this.webPage = webPage;
    }

    public List<AddressDto> getShops() {
        return shops;
    }

    public void setShops(final List<AddressDto> shops) {
        this.shops = shops;
    }

    public List<ShareForCompanyDto> getShares() {
        return shares;
    }

    public void setShares(final List<ShareForCompanyDto> shares) {
        this.shares = shares;
    }

//    public TypeContact getTypeContact() {
//        return typeContact;
//    }
//
//    public void setTypeContact(TypeContact typeContact) {
//        this.typeContact = typeContact;
//    }
//
//    public ContactDto getContact() {
//        return contact;
//    }
//
//    public void setContact(ContactDto contact) {
//        this.contact = contact;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getShareDates() {
//        return shareDates;
//    }
//
//    public void setShareDates(String shareDates) {
//        this.shareDates = shareDates;
//    }
//
//    public ShareState getState() {
//        return state;
//    }
//
//    public void setState(ShareState state) {
//        this.state = state;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getProductLableUrl() {
//        return productLableUrl;
//    }
//
//    public void setProductLableUrl(String productLableUrl) {
//        this.productLableUrl = productLableUrl;
//    }
//
//    public String getShareId() {
//        return shareId;
//    }
//
//    public void setShareId(String shareId) {
//        this.shareId = shareId;
//    }
//
//    public Integer getPickedItemsCount() {
//        return pickedItemsCount;
//    }
//
//    public void setPickedItemsCount(Integer pickedItemsCount) {
//        this.pickedItemsCount = pickedItemsCount;
//    }
//
//    public Integer getAllItemsCount() {
//        return allItemsCount;
//    }
//
//    public void setAllItemsCount(Integer allItemsCount) {
//        this.allItemsCount = allItemsCount;
//    }

}

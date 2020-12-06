package com.sergo.wic.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sergo.wic.dto.ContactDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileResponse extends ResponseContent {

    public UserProfileResponse(){}

    public UserProfileResponse(String userName, String photoUrl, ContactDto contact){
        this.userName = userName;
        this.photoUrl = photoUrl;
        this.contact = contact;
    }

    private String userName;
    private String photoUrl;
    private Integer winCount;
    private Float range;
    private List<ContactDto> contacts;
    private ContactDto contact;

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(final Integer winCount) {
        this.winCount = winCount;
    }

    public Float getRange() {
        return range;
    }

    public void setRange(final Float range) {
        this.range = range;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(final List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public UserProfileResponse(String userName, String photoUrl, Integer winCount, Float range, List<ContactDto> contacts){
        this.userName = userName;
        this.photoUrl = photoUrl;
        this.winCount = winCount;
        this.range = range;
        this.contacts = contacts;
    }
}

package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ContactDto;

import java.util.List;

public class UserProfileResponse extends Response {

    private String userName;
    private String photoUrl;
    private Integer winCount;
    private Float range;
    private List<Integer> pointsCount;
    private List<ContactDto> contacts;

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

    public List<Integer> getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(final List<Integer> pointsCount) {
        this.pointsCount = pointsCount;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(final List<ContactDto> contacts) {
        this.contacts = contacts;
    }
}

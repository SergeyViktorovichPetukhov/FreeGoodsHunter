package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleRegistrationDto {
    @JsonProperty("login")
    private String login;
    private String phone;
    private String placeID;

    public GoogleRegistrationDto(String login, String phone, String placeID) {
        this.placeID = placeID;
        this.login = login;
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }
}

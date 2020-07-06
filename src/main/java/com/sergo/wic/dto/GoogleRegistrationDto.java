package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleRegistrationDto {
    @JsonProperty("login")
    private String login;
    private String phone;
    private String placeName;

    public GoogleRegistrationDto(String login, String phone, String placeName) {
        this.placeName = placeName;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}

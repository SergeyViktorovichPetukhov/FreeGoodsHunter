package com.sergo.wic.dto;

public class RegistrationDto {
    private String login;
    private String phone;

    public RegistrationDto(String login, String phone) {
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
}

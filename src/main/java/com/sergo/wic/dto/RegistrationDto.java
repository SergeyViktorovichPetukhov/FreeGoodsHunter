package com.sergo.wic.dto;

public class RegistrationDto {
    private String login;
    private String address;
    private String phone;

    public RegistrationDto(String login, String address, String phone) {
        this.login = login;
        this.address = address;
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

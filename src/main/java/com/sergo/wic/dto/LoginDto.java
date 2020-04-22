package com.sergo.wic.dto;

public class LoginDto {
    public LoginDto(){}
    private String login;

    public LoginDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

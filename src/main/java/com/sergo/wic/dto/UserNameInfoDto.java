package com.sergo.wic.dto;

public class UserNameInfoDto {
    String login;
    String firstNameAndLastName;

    public UserNameInfoDto(String login, String firstNameAndLastName) {
        this.login = login;
        this.firstNameAndLastName = firstNameAndLastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstNameAndLastName() {
        return firstNameAndLastName;
    }

    public void setFirstNameAndLastName(String firstNameAndLastName) {
        this.firstNameAndLastName = firstNameAndLastName;
    }
}

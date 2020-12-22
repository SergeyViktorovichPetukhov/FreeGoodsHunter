package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto {
    public LoginDto(){}
    private String login;
    private String firstNameAndLastName;
    boolean isRequestedFromMenu;

    public LoginDto(String login) {
        this.login = login;
    }

    public String getFirstNameAndLastName() {
        return firstNameAndLastName;
    }

    public void setFirstNameAndLastName(String firstNameAndLastName) {
        this.firstNameAndLastName = firstNameAndLastName;
    }

    public boolean isRequestedFromMenu() {
        return isRequestedFromMenu;
    }

    public void setRequestedFromMenu(boolean requestedFromMenu) {
        isRequestedFromMenu = requestedFromMenu;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

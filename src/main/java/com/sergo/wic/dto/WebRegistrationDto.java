package com.sergo.wic.dto;

public class WebRegistrationDto {
    private String login;
    private String userContact;
    private String url;

    public WebRegistrationDto(String login, String userContact, String url) {
        this.login = login;
        this.userContact = userContact;
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


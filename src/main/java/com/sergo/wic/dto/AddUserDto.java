package com.sergo.wic.dto;

public class AddUserDto {
    private String login;
    private String contact;

    public AddUserDto(String login, String contact) {
        this.login = login;
        this.contact = contact;
    }

    public AddUserDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

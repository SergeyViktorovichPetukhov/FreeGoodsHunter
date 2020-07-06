package com.sergo.wic.dto;

public class MaxCountItemsDto {
    private String login;
    private String locality;

    public MaxCountItemsDto() {
    }

    public MaxCountItemsDto(String login, String locality) {
        this.login = login;
        this.locality = locality;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}

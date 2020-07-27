package com.sergo.wic.dto;

public class MaxCountItemsDto {
    private String login;
    private String country;
    private String settlement;

    public MaxCountItemsDto() {
    }

    public MaxCountItemsDto(String login, String country, String settlement) {
        this.login = login;
        this.country = country;
        this.settlement = settlement;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }
}

package com.sergo.wic.dto;

public class MaxCountItemsDto {
    private String login;
    private GeoLocationData geoLocationData;

    public MaxCountItemsDto() {
    }

    public MaxCountItemsDto(String login, GeoLocationData geoLocationData) {
        this.login = login;
        this.geoLocationData = geoLocationData;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public GeoLocationData getGeoLocationData() {
        return geoLocationData;
    }

    public void setGeoLocationData(GeoLocationData geoLocationData) {
        this.geoLocationData = geoLocationData;
    }
}

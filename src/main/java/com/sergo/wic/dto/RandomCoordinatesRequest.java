package com.sergo.wic.dto;

public class RandomCoordinatesRequest {

    private GeoLocationData geoLocationData;
    private String login;
    private int quantity;

    public RandomCoordinatesRequest(GeoLocationData geoLocationData, String login, int quantity) {
        this.geoLocationData = geoLocationData;
        this.login = login;
        this.quantity = quantity;
    }

    public RandomCoordinatesRequest() {
    }

    public GeoLocationData getGeoLocationData() {
        return geoLocationData;
    }

    public void setGeoLocationData(GeoLocationData geoLocationData) {
        this.geoLocationData = geoLocationData;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

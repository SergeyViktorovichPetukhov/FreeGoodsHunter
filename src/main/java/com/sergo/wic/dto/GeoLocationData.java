package com.sergo.wic.dto;

public class GeoLocationData {
    private String country;
    private String region;
    private String settlement;

    public GeoLocationData(String country, String region, String settlement) {
        this.country = country;
        this.region = region;
        this.settlement = settlement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }
}

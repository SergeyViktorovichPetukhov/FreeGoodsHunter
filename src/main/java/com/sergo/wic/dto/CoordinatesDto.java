package com.sergo.wic.dto;

public class CoordinatesDto {
    private Double lat;
    private Double lon;
    public CoordinatesDto(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}

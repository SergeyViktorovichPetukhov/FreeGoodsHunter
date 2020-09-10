package com.sergo.wic.dto;

public class CoordinatesDto {
    private Double latitude;
    private Double longitude;
    public CoordinatesDto(double lat, double longitude){
        this.latitude = lat;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double lat) {
        this.latitude = lat;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

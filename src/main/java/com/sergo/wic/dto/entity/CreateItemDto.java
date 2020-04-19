package com.sergo.wic.dto.entity;

import javax.validation.constraints.NotNull;

public class CreateItemDto {

    @NotNull(message = "lon must not be null")
    private Double lon;

    @NotNull(message = "lat must not be null")
    private Double lat;

    public CreateItemDto() {
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}

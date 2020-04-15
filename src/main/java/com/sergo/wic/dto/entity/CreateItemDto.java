package com.sergo.wic.dto.entity;

import javax.validation.constraints.NotNull;

public class CreateItemDto {

    @NotNull(message = "longitude must not be null")
    private Double longitude;

    @NotNull(message = "latitude must not be null")
    private Double latitude;

    public CreateItemDto() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

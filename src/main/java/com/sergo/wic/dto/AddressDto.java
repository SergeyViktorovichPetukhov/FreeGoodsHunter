package com.sergo.wic.dto;

import javax.validation.constraints.NotNull;

public class AddressDto {

    @NotNull
    private String country;

    @NotNull
    private String region;

    @NotNull
    private String city;

    @NotNull
    private String addressLine;

    public AddressDto() {
    }

    public AddressDto(final String country, final String region, final String city, final String adressLine) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.addressLine = adressLine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(final String adressLine) {
        this.addressLine = adressLine;
    }
}

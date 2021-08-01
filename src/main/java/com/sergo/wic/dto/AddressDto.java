package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull
    private String country;

    @NotNull
    private String region;

    @NotNull
    private String city;

    @NotNull
    private String addressLine;

    private CoordinatesDto coordinates;

    private String shopId;


}

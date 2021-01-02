package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@NoArgsConstructor
@Data
public class CreateShareDto {

    @NotBlank(message = "login must not be null")
    private String login;

    @NotBlank(message = "productName must not be blank")
    private String productName;

    private String productDescription;

    private String productWebsite;

    private Double productPrice;

    @Positive(message = "productCount must be greater than 0")
    private Integer productCount;

    @Positive(message = "announcementDuration must be greater than 0")
    private Integer announcementDuration;

    @Positive(message = "shareDuration must be greater than 0")
    private Integer shareDuration;

    @Positive(message = "afterShareDuration must be greater than 0")
    private Integer afterShareDuration;

    @Positive(message = "color must not be blank")
    private String color;

    @NotBlank(message = "country must not be blank")
    private String placeCountry;

    @NotBlank(message = "region must not be blank")
    private String placeRegion;

    @NotBlank(message = "city must not be blank")
    private String placeCity;

    @Valid
    @NotEmpty(message = "items must not be empty")
    @JsonProperty(value = "items")
    private List<ItemDto> items;

}

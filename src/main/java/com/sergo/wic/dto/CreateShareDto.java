package com.sergo.wic.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class CreateShareDto {

    @NotBlank(message = "login must not be blank")
    private String login;

  //  @NotNull(message = "no image")
//    private MultipartFile photoProduct;

    @NotBlank(message = "productName must not be blank")
    private String productName;

    @NotBlank(message = "description must not be blank")
    private String description;

    //надо добавить проверку
    private String linkOnProductUrl;

    private Double productPrice;

//    private Integer pickedItemsCount;

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


//    @Valid
//    @NotNull
//    private AddressDto placeAddress;

    @Valid
    @NotEmpty(message = "items must not be empty")
 //   @JsonProperty
    private List<CreateItemDto> items;


    public CreateShareDto() {
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }


//    public Integer getPickedItemsCount() {
//        return pickedItemsCount;
//    }
//
//    public void setPickedItemsCount(Integer pickedItemsCount) {
//        this.pickedItemsCount = pickedItemsCount;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLinkOnProductUrl() {
        return linkOnProductUrl;
    }

    public void setLinkOnProductUrl(String linkOnProductUrl) {
        this.linkOnProductUrl = linkOnProductUrl;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getAnnouncementDuration() {
        return announcementDuration;
    }

    public void setAnnouncementDuration(Integer announcementDuration) {
        this.announcementDuration = announcementDuration;
    }

    public Integer getShareDuration() {
        return shareDuration;
    }

    public void setShareDuration(Integer shareDuration) {
        this.shareDuration = shareDuration;
    }

    public Integer getAfterShareDuration() {
        return afterShareDuration;
    }

    public void setAfterShareDuration(Integer afterShareDuration) {
        this.afterShareDuration = afterShareDuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public AddressDto getPlaceAddress() {
//        return placeAddress;
//    }
//
//    public void setPlaceAddress(AddressDto placeAddress) {
//        this.placeAddress = placeAddress;
//    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPlaceCountry() {
        return placeCountry;
    }

    public void setPlaceCountry(String placeCountry) {
        this.placeCountry = placeCountry;
    }

    public String getPlaceRegion() {
        return placeRegion;
    }

    public void setPlaceRegion(String placeRegion) {
        this.placeRegion = placeRegion;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity;
    }

    public List<CreateItemDto> getItems() {
        return items;
    }

    public void setItems(List<CreateItemDto> items) {
        this.items = items;
    }
}

package com.sergo.wic.dto.entity;

import com.sergo.wic.dto.AddressDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class CreateShareDto {

    @NotBlank(message = "description must not be blank")
    private String description;

    @Positive(message = "countOfProduct must be greater than 0")
    private Integer countOfProduct;

    @NotBlank(message = "productName must not be blank")
    private String productName;

    private String linkOnProductUrl;

    private Double productPrice;

    @Positive(message = "announcementDuration must be greater than 0")
    private Integer announcementDuration;

    @Positive(message = "shareDuration must be greater than 0")
    private Integer shareDuration;

    @Positive(message = "afterShareDuration must be greater than 0")
    private Integer afterShareDuration;

    @NotBlank(message = "color must not be blank")
    private String color;

    @Valid
    @NotNull
    private AddressDto placeAddress;

    @Valid
    @NotEmpty(message = "items must not be empty")
    private List<CreateItemDto> items;

    public CreateShareDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(Integer countOfProduct) {
        this.countOfProduct = countOfProduct;
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

    public AddressDto getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(AddressDto placeAddress) {
        this.placeAddress = placeAddress;
    }

    public List<CreateItemDto> getItems() {
        return items;
    }

    public void setItems(List<CreateItemDto> items) {
        this.items = items;
    }
}

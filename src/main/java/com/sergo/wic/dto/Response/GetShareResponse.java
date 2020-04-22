package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.UserDto;
import com.sergo.wic.dto.ItemDto;

import java.util.List;

public class GetShareResponse extends Response {

    private String productName;
    private String productDescription;
    private String linkOnProduct;
    private double productPrice;
    private String code;
    private List<UserDto> usersWithShareItems;

    private String country;
    private String region;
    private String city;

    private int color;
    private List<ItemDto> points;

    public GetShareResponse(){
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getLinkOnProduct() {
        return linkOnProduct;
    }


    public void setLinkOnProduct(String linkOnProduct) {
        this.linkOnProduct = linkOnProduct;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserDto> getUsersWithShareItems() {
        return usersWithShareItems;
    }

    public void setUsersWithShareItems(List<UserDto> usersWithShareItems) {
        this.usersWithShareItems = usersWithShareItems;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<ItemDto> getPoints() {
        return points;
    }

    public void setPoints(List<ItemDto> points) {
        this.points = points;
    }
}

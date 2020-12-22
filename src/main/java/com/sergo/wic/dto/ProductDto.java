package com.sergo.wic.dto;

public class ProductDto {
    private String productName;
    private String logo;
    private String description;
    private double price;
    private String webSite;

    public ProductDto() {
    }

    public ProductDto(String productName, String logo, String description, double price, String webSite) {
        this.productName = productName;
        this.logo = logo;
        this.description = description;
        this.price = price;
        this.webSite = webSite;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}

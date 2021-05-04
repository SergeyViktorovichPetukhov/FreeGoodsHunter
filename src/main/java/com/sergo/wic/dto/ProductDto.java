package com.sergo.wic.dto;

public class ProductDto {
    private String name;
    private String labelUrl;
    private String description;
    private double price;
    private String webSite;

    public ProductDto() {
    }

    public ProductDto(String name, String labelUrl, String description, double price, String webSite) {
        this.name = name;
        this.labelUrl = labelUrl;
        this.description = description;
        this.price = price;
        this.webSite = webSite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
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

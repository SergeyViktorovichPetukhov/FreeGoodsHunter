package com.sergo.wic.dto;

public class ShortShareInfoDto {
    private String pictureOfCompanyUrl;
    private String companyName;
    private Integer allItemsCount;
    private Integer pickedItemsCount;
    private String color;
    private Integer state;

    public ShortShareInfoDto(final String pictureOfCompanyUrl, final String companyName, final Integer allItemsCount, final
    Integer pickedItemsCount, final String color, final Integer state) {
        this.pictureOfCompanyUrl = pictureOfCompanyUrl;
        this.companyName = companyName;
        this.allItemsCount = allItemsCount;
        this.pickedItemsCount = pickedItemsCount;
        this.color = color;
        this.state = state;
    }

    public String getPictureOfCompanyUrl() {
        return pictureOfCompanyUrl;
    }

    public void setPictureOfCompanyUrl(final String pictureOfCompanyUrl) {
        this.pictureOfCompanyUrl = pictureOfCompanyUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(final Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public Integer getPickedItemsCount() {
        return pickedItemsCount;
    }

    public void setPickedItemsCount(final Integer pickedItemsCount) {
        this.pickedItemsCount = pickedItemsCount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public Integer getState() {
        return state;
    }

    public void setState(final Integer stateOfShare) {
        this.state = stateOfShare;
    }

}

package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ShareForCompanyDto {

    private String shareId;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date date;
    private String pictureOfCompanyUrl;
    private Integer allItemsCount;
    private Integer pickedItemsCount;
    private Integer state;

    public ShareForCompanyDto(final String shareId, final Date date, final String pictureOfCompanyUrl, final Integer
            allItemsCount, final Integer pickedItemsCount, final Integer state) {
        this.shareId = shareId;
        this.date = date;
        this.pictureOfCompanyUrl = pictureOfCompanyUrl;
        this.allItemsCount = allItemsCount;
        this.pickedItemsCount = pickedItemsCount;
        this.state = state;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(final String shareId) {
        this.shareId = shareId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getPictureOfCompanyUrl() {
        return pictureOfCompanyUrl;
    }

    public void setPictureOfCompanyUrl(final String pictureOfCompanyUrl) {
        this.pictureOfCompanyUrl = pictureOfCompanyUrl;
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

    public Integer getState() {
        return state;
    }

    public void setState(final Integer state) {
        this.state = state;
    }
}

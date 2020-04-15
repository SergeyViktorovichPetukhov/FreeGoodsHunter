package com.sergo.wic.dto;

public class AllItemsDto {
    private String shareId;
    private String companyName;
    private String pictureCompanyUrl;
    private String pictureProductUrl;
    private Integer allItemsCount;
    private Integer pickedItemsCount;

    public AllItemsDto() {
    }

    public AllItemsDto(String shareId, String companyName, String pictureCompanyUrl, String pictureProductUrl,
                       Integer allItemsCount, Integer pickedItemsCount) {
        this.shareId = shareId;
        this.companyName = companyName;
        this.pictureCompanyUrl = pictureCompanyUrl;
        this.pictureProductUrl = pictureProductUrl;
        this.allItemsCount = allItemsCount;
        this.pickedItemsCount = pickedItemsCount;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPictureCompanyUrl() {
        return pictureCompanyUrl;
    }

    public void setPictureCompanyUrl(String pictureCompanyUrl) {
        this.pictureCompanyUrl = pictureCompanyUrl;
    }

    public String getPictureProductUrl() {
        return pictureProductUrl;
    }

    public void setPictureProductUrl(String pictureProductUrl) {
        this.pictureProductUrl = pictureProductUrl;
    }

    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public Integer getPickedItemsCount() {
        return pickedItemsCount;
    }

    public void setPickedItemsCount(Integer pickedItemsCount) {
        this.pickedItemsCount = pickedItemsCount;
    }
}

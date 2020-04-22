package com.sergo.wic.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
    private String login;
    private byte[] userPhoto;
    private Integer pickedItemsCount;
    private Integer allItemsCount;


    public UserDto() {
    }

    public UserDto(String login, byte[] userPhoto, Integer pickedItemsCount, Integer allItemsCount) {
        this.login = login;
        this.userPhoto = userPhoto;
        this.pickedItemsCount = pickedItemsCount;
        this.allItemsCount = allItemsCount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public Integer getPickedItemsCount() {
        return pickedItemsCount;
    }

    public void setPickedItemsCount(final Integer pickedItemsCount) {
        this.pickedItemsCount = pickedItemsCount;
    }

    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
    }
}

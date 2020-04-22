package com.sergo.wic.dto;

import com.sergo.wic.entities.Item;

public class ItemDto {

    private String userLogin;
    private Item point;
    private String shareId;


    public ItemDto() {
    }

    public ItemDto(Item item) {
        this.point = item;

    }

    public ItemDto(Item item, String shareId, String login) {
        this.point = item;
        this.shareId = shareId;
        this.userLogin = login;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String id) {
        this.shareId = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Item getPoint() {
        return point;
    }

    public void setPoint(Item point) {
        this.point = point;
    }
}

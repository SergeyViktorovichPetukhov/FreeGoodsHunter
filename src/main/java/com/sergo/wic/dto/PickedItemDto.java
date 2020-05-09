package com.sergo.wic.dto;

import com.sergo.wic.entities.Item;

public class PickedItemDto {

    private String userLogin;
    private Item point;
    private String shareId;
    private Long itemId;


    public PickedItemDto() {
    }

    public PickedItemDto(Item item) {
        this.point = item;

    }

    public PickedItemDto(Item item, Long itemId, String shareId, String login) {
        this.point = item;
        this.itemId = itemId;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setPoint(Item point) {
        this.point = point;
    }
}

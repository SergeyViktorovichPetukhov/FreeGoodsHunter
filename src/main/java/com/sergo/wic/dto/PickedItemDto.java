package com.sergo.wic.dto;

import com.sergo.wic.entities.Item;

public class PickedItemDto {

    private String login;
    private ItemDto point;
    private String shareId;
    private String itemId;


    public PickedItemDto() {
    }

    public PickedItemDto(ItemDto item) {
        this.point = item;

    }

    public PickedItemDto(ItemDto item, String itemId, String login) {
        this.point = item;
        this.itemId = itemId;
        this.shareId = shareId;
        this.login = login;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String id) {
        this.shareId = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ItemDto getPoint() {
        return point;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setPoint(ItemDto point) {
        this.point = point;
    }
}

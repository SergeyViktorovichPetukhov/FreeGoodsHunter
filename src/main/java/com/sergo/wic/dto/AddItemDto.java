package com.sergo.wic.dto;

public class AddItemDto {
    public AddItemDto() {
    }

    public AddItemDto( ItemDto coordinates) {
    //    this.login = login;
        this.coordinates = coordinates;
    }

//    private String login;
    private ItemDto coordinates;
    private String shareId;
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

    public ItemDto getCoordinates() {
        return coordinates;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public void setCoordinates(ItemDto coordinates) {
        this.coordinates = coordinates;
    }
}

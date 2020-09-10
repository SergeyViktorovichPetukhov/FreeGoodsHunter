package com.sergo.wic.dto;

public class ItemDto {

    public ItemDto(){}

    public ItemDto(CoordinatesDto dto, String itemId) {
        this.itemId = itemId;
        this.coordinates = dto;
    }

   private CoordinatesDto coordinates;

//    private String shareId;
//
//    private String userId;

    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public CoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }
}

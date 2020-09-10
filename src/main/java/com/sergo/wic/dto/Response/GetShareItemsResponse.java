package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ItemDto;

import java.util.List;

public class GetShareItemsResponse extends ResponseContent {
    private List<ItemDto> items;
    public GetShareItemsResponse(List<ItemDto> dtos){
        this.items = dtos;
    }
    public GetShareItemsResponse(){}

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}

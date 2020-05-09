package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.PickedItemDto;

import java.util.List;

public class ShowItemsResponse extends Response {
    List<ItemDto> items;
    public ShowItemsResponse(List<ItemDto> items, boolean isSuccess, int errorCode){
        super(isSuccess,errorCode);
        this.items = items;
    }
    public ShowItemsResponse(){}

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}

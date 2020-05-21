package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.ResponseContent;

import java.util.List;

public class GetShareItemsResponse extends ResponseContent {
    private List<ItemDto> itemDtos;
    public GetShareItemsResponse(List<ItemDto> dtos){
        this.itemDtos = dtos;
    }
    public GetShareItemsResponse(){}

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }
}

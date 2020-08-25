package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.dto.ResponseContent;

import java.util.List;

public class PickItemResponse extends ResponseContent {

    private List<PickedItemDto> items;

    public List<PickedItemDto> getItems() {
        return items;
    }

    public void addItem(PickedItemDto item){
        items.add(item);
    }

    public void setItems(List<PickedItemDto> items) {
        this.items = items;
    }

    public PickItemResponse(List<PickedItemDto> items) {
        this.items = items;
    }
}

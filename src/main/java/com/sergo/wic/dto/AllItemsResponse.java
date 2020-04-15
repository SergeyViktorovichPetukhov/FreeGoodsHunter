package com.sergo.wic.dto;

import java.util.ArrayList;
import java.util.List;

public class AllItemsResponse extends Response {

    private List<AllItemsDto> allItems;

    public AllItemsResponse() {
        allItems = new ArrayList<>();
        setSuccess(false);
        setErrorCode(0);
    }

    public AllItemsResponse(List<AllItemsDto> allItems) {
        this.allItems = allItems;
    }

    public AllItemsResponse(boolean isSuccess, int errorCode, List<AllItemsDto> allItems) {
        super(isSuccess, errorCode);
        this.allItems = allItems;
    }

    public List<AllItemsDto> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<AllItemsDto> allItems) {
        this.allItems = allItems;
    }
}

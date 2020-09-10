package com.sergo.wic.dto.Response;

public class MaxCountItemsResponse extends ResponseContent {
    private Integer maxCountItems;

    public MaxCountItemsResponse(Integer maxCountItems) {
        this.maxCountItems = maxCountItems;
    }
    public MaxCountItemsResponse(){}

    public Integer getMaxCountItems() {
        return maxCountItems;
    }

    public void setMaxCountItems(Integer maxCountItems) {
        this.maxCountItems = maxCountItems;
    }
}

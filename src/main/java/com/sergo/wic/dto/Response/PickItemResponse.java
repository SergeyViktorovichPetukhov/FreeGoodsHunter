package com.sergo.wic.dto.Response;

public class PickItemResponse extends Response {

    private Integer isPickedFullItemsForOneProduct;
    private String code;

    public PickItemResponse(boolean isSuccess, int errorCode, Integer isPickedFullItemsForOneProduct){
        super(isSuccess,errorCode);
        this.isPickedFullItemsForOneProduct = isPickedFullItemsForOneProduct;
    }



}

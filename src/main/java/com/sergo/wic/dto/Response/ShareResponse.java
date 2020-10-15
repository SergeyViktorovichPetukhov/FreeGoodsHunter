package com.sergo.wic.dto.Response;

public class ShareResponse extends ResponseContent {

    private String share_id;

    public ShareResponse(String share_id) {
        this.share_id = share_id;
    }


    public String getId() {
        return share_id;
    }

    public void setId(String share_id) {
        this.share_id = share_id;
    }
}

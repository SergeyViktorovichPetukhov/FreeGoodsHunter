package com.sergo.wic.dto.Response;

public class ShareResponse extends Response {

    private String share_id;

    public ShareResponse(String share_id) {
        this.share_id = share_id;
    }

    public ShareResponse(boolean success, String reason){
        if (!success) {
            setSuccess(success);
            setId(null);
            setErrorCode(1);
            setMessage(reason);
        }
    }

    public String getId() {
        return share_id;
    }

    public void setId(String share_id) {
        this.share_id = share_id;
    }
}

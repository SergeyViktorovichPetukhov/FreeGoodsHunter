package com.sergo.wic.dto.Response;

public class ShareResponse extends Response {

    private String share_id;

    public ShareResponse(final String share_id) {
        this.share_id = share_id;
    }

    public ShareResponse(boolean success){
        if (!success) {
            setSuccess(success);
            setErrorCode(1);
            setErrorMessage("file wasn't upload, try again");
        }
    }

    public String getId() {
        return share_id;
    }

    public void setId(String share_id) {
        this.share_id = share_id;
    }
}

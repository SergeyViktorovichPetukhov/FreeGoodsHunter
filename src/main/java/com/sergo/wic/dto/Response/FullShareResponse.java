package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ShareDto;

public class FullShareResponse extends Response {

    private ShareDto share;

    public FullShareResponse(final ShareDto share) {
        this.share = share;
    }

    public ShareDto getShare() {
        return share;
    }

    public void setShare(ShareDto share) {
        this.share = share;
    }
}

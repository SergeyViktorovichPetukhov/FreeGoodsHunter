package com.sergo.wic.dto;

import com.sergo.wic.dto.entity.ShareDto;

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

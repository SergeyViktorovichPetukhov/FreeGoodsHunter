package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ShortShareInfoDto;

import java.util.ArrayList;
import java.util.List;

public class SharesResponse extends Response {

    private List<ShortShareInfoDto> shares;

    public SharesResponse() {
        shares = new ArrayList<>();
        setSuccess(false);
        setErrorCode(0);
    }



    public SharesResponse(final List<ShortShareInfoDto> shares) {
        this.shares = shares;
    }

    public List<ShortShareInfoDto> getShares() {
        return shares;
    }

    public void setShares(final List<ShortShareInfoDto> shares) {
        this.shares = shares;
    }

}

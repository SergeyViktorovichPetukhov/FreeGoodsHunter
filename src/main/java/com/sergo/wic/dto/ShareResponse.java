package com.sergo.wic.dto;

public class ShareResponse extends Response {

    private Long id;

    public ShareResponse(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

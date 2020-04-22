package com.sergo.wic.dto;

public class RequestGetShareDto {
    private String login;
    private String shareId;

    public RequestGetShareDto(String login, String shareId) {
        this.login = login;
        this.shareId = shareId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }
}

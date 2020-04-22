package com.sergo.wic.dto;

public class LoginAndShareDto {
    private String login;
    private String shareId;

    public LoginAndShareDto(String login, String shareId) {
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

package com.sergo.wic.dto;

public class HtmlByEmailDto {
    private String url;
    private String email;
    public HtmlByEmailDto(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HtmlByEmailDto(String url, String email) {
        this.url = url;
        this.email = email;
    }
}

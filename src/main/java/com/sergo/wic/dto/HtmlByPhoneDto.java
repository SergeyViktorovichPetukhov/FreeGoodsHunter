package com.sergo.wic.dto;

public class HtmlByPhoneDto {
    public HtmlByPhoneDto(){}
    public HtmlByPhoneDto(String url, String phone){
        this.url = url;
        this.phone = phone;
    }
    private String url;
    private String phone;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

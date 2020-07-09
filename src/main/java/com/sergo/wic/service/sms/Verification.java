package com.sergo.wic.service.sms;

public class Verification {
    private String phone;
    private String code;

    public Verification(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }
    public Verification(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

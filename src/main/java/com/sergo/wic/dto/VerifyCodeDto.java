package com.sergo.wic.dto;

public class VerifyCodeDto {
    private String regId;
    private String login;
    private String code;


    public VerifyCodeDto(String regId, String code) {
        this.code = code;
        this.regId = regId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

}

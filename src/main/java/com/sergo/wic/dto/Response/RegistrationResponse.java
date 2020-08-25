package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ResponseContent;

public class RegistrationResponse extends ResponseContent {

    private String regID;

    public String getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        this.regID = regID;
    }

    public RegistrationResponse(String regID) {
        this.regID = regID;
    }
}

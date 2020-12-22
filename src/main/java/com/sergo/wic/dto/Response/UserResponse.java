package com.sergo.wic.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends ResponseContent {

    private boolean hasCompany;
    private boolean isFirstLogin;


    public UserResponse(boolean hasCompany){
        this.hasCompany = hasCompany;
    }

    public UserResponse(boolean hasCompany , boolean isFirstLogin){
        this.hasCompany = hasCompany;
        this.isFirstLogin = isFirstLogin;
    }


    public boolean isHasCompany() {
        return hasCompany;
    }

    public void setHasCompany(boolean hasCompany) {
        this.hasCompany = hasCompany;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
}

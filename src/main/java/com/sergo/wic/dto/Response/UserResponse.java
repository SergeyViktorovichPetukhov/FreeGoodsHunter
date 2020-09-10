package com.sergo.wic.dto.Response;

public class UserResponse extends ResponseContent {

    private boolean hasCompany;


    public UserResponse(boolean hasCompany){
        this.hasCompany = hasCompany;
    }


    public boolean isHasCompany() {
        return hasCompany;
    }

    public void setHasCompany(boolean hasCompany) {
        this.hasCompany = hasCompany;
    }

}

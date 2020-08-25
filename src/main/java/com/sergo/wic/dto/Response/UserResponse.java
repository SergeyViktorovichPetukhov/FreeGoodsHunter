package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ResponseContent;

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

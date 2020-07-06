package com.sergo.wic.dto.Response;

public class UserResponse extends Response {

    private boolean hasCompany;

    public UserResponse(boolean hasCompany){
        if(hasCompany){
            this.setSuccess(true);
            this.setErrorCode(0);
            this.hasCompany = true;
        }
        else {
            this.setSuccess(true);
            this.setErrorCode(1);
            this.setMessage("user hasn't company");
            this.hasCompany = false;
        }
    }

    public UserResponse( boolean hasCompany, boolean noUser){
        this.setSuccess(true);
        this.setErrorCode(2);
        this.setMessage("no such user");
        this.hasCompany = false;
    }



    public boolean isHasCompany() {
        return hasCompany;
    }

    public void setHasCompany(boolean hasCompany) {
        this.hasCompany = hasCompany;
    }
}

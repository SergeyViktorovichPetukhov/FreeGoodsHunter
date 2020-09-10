package com.sergo.wic.dto.Response;

public class IsCompanyRegInProcessResponse extends ResponseContent {
   private boolean IsCompanyRegInProcess;

    public IsCompanyRegInProcessResponse(boolean isCompanyRegInProcess) {
        IsCompanyRegInProcess = isCompanyRegInProcess;
    }

    public boolean isCompanyRegInProcess() {
        return IsCompanyRegInProcess;
    }

    public void setCompanyRegInProcess(boolean companyRegInProcess) {
        IsCompanyRegInProcess = companyRegInProcess;
    }
}

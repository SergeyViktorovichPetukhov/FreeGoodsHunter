package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ResponseContent;

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

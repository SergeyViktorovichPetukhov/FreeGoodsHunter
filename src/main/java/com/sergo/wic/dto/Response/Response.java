package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ResponseContent;

public class Response {

    private boolean isSuccess;
    private int errorCode;
    private String errorMessage;
    private ResponseContent content;
//    private List<String> errors;

    public Response() {
    }

//    public Response(List<String> errors) {
//        this.errors = errors;
//    }

    public Response(final boolean isSuccess, final int errorCode) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
    }

    public Response(final boolean isSuccess,final int errorCode,final String errorMessage) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

        public Response(boolean isSuccess, int errorCode, ResponseContent content) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.content = content;
    }

//    public List<String> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<String> errors) {
//        this.errors = errors;
//    }


    public ResponseContent getContent() {
        return content;
    }

    public void setContent(ResponseContent content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(final boolean success) {
        isSuccess = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

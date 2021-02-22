package com.sergo.wic.utils;

public enum PhotoPaths {

    USER("D:\\\\photoFGH\\users\\"),
    PRODUCT ("D:\\\\photoFGH\\products\\"),
    COMPANY_LOGO("D:\\\\photoFGH\\logo\\"),

    SERVER_USER("photoFGH\\users\\"),
    SERVER_PRODUCT ("photoFGH\\products\\"),
    SERVER_COMPANY_LOGO("photoFGH\\logo\\");

    private String value;

    PhotoPaths(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}

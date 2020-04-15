package com.sergo.wic.dto;

import java.util.Objects;

public class CompanyDto {
    private String login;
    private String address;
    private String phone;
    private String internetShop;
    private Integer code;

    public CompanyDto(String login, String address, String phone, Integer code) {
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.code = code;
    }
    public CompanyDto(String login, String address, String phone, Integer code,String internetShop) {
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.internetShop = internetShop;
        this.code = code;
    }

    public String getInternetShop() {
        return internetShop;
    }

    public void setInternetShop(String internetShop) {
        this.internetShop = internetShop;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDto that = (CompanyDto) o;
        return login.equals(that.login) &&
                address.equals(that.address) &&
                phone.equals(that.phone) &&
                code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, address, phone, code);
    }
}

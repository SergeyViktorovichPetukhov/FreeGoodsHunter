package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDto {
    private String login;
    private String address;
    private String phone;
    private String internetShop;
    private Integer code;
    private String name;
    private String info;
    private String contact;
    private Boolean isVerificated;

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

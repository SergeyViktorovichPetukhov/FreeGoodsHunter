package com.sergo.wic.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class UserResponse extends ResponseContent {
    private Boolean hasCompany;
    private Boolean isFirstLogin;
    private String firstNameAndLastName;
    private String photoUrl;
    private String phone;

    public UserResponse(Boolean hasCompany) {
        this.hasCompany = hasCompany;
    }

    public UserResponse(Boolean hasCompany, Boolean isFirstLogin) {
        this.hasCompany = hasCompany;
        this.isFirstLogin = isFirstLogin;
    }

    public UserResponse(String firstNameAndLastName, String photoUrl, String phone) {
        this.firstNameAndLastName = firstNameAndLastName;
        this.photoUrl = photoUrl;
        this.phone = phone;
    }
}

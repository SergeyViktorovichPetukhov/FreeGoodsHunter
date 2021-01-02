package com.sergo.wic.dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadImageDto {
    private String login;
    private String productName;
    private String companyLogo;
}

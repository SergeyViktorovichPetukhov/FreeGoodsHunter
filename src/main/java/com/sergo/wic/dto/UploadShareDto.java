package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UploadShareDto {
    private String color;
    private Date date;
    private String productPhotoUrl;
    private String status;
    private String productName;
    private Integer allItemsCount;
    private Integer pickedItemsCount;
    private Double productPrice;
    private String productWebsite;
    private String productDescription;
    private String shareId;
    private Integer companyId;
}

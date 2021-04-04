package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.UserDto;
import com.sergo.wic.dto.PickedItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetShareResponse extends ResponseContent {

    private String productName;
    private String productDescription;
    private String linkOnProduct;
    private double productPrice;
    private String code;
    private List<UserDto> usersWithShareItems;

    private String country;
    private String region;
    private String city;

    private String color;
    private List<PickedItemDto> points;

}

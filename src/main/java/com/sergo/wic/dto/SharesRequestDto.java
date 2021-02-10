package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SharesRequestDto {
    private String login;
    private String country;
    private String region;
    private String city;
    private CoordinatesDto coordinates;
}

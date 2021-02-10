package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShareListDto {
    private String country;
    private String region;
    private String city;
    private CoordinatesDto coordinates;
}

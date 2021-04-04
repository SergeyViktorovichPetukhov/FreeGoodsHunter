package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomCoordinatesRequest {
    private GeoLocationData geoLocationData;
    private String login;
    private int quantity;
}

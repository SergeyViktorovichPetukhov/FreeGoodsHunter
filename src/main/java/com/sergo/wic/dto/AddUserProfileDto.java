package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddUserProfileDto {
    private String login;
    private String userName;
    private String region;
    private Integer collectionArea;
    private Double range;
    private List<ContactDto> contacts;
}

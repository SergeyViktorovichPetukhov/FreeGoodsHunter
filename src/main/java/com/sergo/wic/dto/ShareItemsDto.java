package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShareItemsDto {
    private List<ItemDto> items = new ArrayList<>();
    private String color;
    private String shareId;
}

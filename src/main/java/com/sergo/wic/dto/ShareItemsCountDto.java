package com.sergo.wic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShareItemsCountDto {
    private Integer pickedItemsCount;
    private Integer allItemsCount;
    private List<ItemDto> items = new ArrayList<>();
}

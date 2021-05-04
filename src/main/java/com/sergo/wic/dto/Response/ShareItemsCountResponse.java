package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.ShareItemsCountDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class ShareItemsCountResponse extends ResponseContent {
    private Integer pickedItemsCount;
    private Integer allItemsCount;
    private List<ItemDto> items;
}

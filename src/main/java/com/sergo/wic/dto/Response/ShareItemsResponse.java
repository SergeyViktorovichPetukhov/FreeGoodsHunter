package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.ShareItemsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ShareItemsResponse extends ResponseContent {
    private List<ShareItemsDto> shareItems;
}

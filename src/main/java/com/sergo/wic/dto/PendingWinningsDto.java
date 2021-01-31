package com.sergo.wic.dto;

import com.sergo.wic.dto.Response.ResponseContent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingWinningsDto extends ResponseContent {
    boolean hasPendingWinnings;
}

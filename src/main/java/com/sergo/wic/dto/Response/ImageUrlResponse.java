package com.sergo.wic.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUrlResponse extends ResponseContent {
    private String ImageUrl;
}

package com.sergo.wic.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllSharesResponse extends ResponseContent {
    private List<GetShareResponse> shares;
}

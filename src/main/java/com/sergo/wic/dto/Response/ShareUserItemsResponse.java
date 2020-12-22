package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShareUserItemsResponse extends ResponseContent {
    private List<UserDto> users;
}

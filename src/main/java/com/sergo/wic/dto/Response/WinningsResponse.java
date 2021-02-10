package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.WinningsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WinningsResponse extends ResponseContent{
    private List<WinningsDto> winnings;
}

package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.CellModelDto;
import com.sergo.wic.entities.enums.ShareCellType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShareCellTypesResponse extends ResponseContent{
    private List<CellModelDto> cellModels;
}

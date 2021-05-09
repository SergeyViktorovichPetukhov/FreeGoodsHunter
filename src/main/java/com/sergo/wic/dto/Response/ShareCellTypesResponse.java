package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.CellModelDto;
import com.sergo.wic.entities.enums.ShareCellType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ShareCellTypesResponse extends ResponseContent{
    private String distanceToNearestItem;
    private List<CellModelDto> cellModels;

    public ShareCellTypesResponse(String distanceToNearestItem, List<CellModelDto> cellModels) {
        this.distanceToNearestItem = distanceToNearestItem;
        this.cellModels = cellModels;
    }

    public ShareCellTypesResponse(List<CellModelDto> cellModels) {
        this.cellModels = cellModels;
    }
}

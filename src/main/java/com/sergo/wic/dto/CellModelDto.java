package com.sergo.wic.dto;

import com.sergo.wic.entities.enums.ShareCellType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellModelDto {
    private String productPhotoUrl;
    private ShareCellType cellType;
    private String productName;
    private String date;
    private int distanceToNearestItem;
    private double productPrice;
    private int pickedItemsCount;
    private int numItemsToWin;
    private boolean isVerificated;
}

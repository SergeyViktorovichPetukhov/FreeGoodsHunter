package com.sergo.wic.dto;

import com.sergo.wic.entities.enums.ShareCellType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellModelDto {
    private String productPhotoUrl;
    private String shareId;
    private String companyId;
    private Integer cellType;
    private String productName;
    private String date;
    private String distanceToNearestItem;
    private String productPrice;
    private int pickedItemsCount;
    private int numItemsToWin;
    private boolean isVerificated;
}

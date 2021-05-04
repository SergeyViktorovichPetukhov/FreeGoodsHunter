package com.sergo.wic.dto;

import com.sergo.wic.entities.enums.PrizeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinningsDto {

    private boolean isViewed;

    private boolean isComplained;

    private boolean isReviewed;

    private String shareId;

    private String companyId;

    private String date;

    private String name;

    private Integer amountOfItems;

    private String prizeUrl;

    private PrizeType prizeType;
}

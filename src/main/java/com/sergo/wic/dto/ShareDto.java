package com.sergo.wic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sergo.wic.entities.enums.ShareState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShareDto {

    private Long id;

    @NotBlank
    private String shareId;

    @NotBlank
    private String description;

    @Positive
    private Integer countOfProduct;

    private String photoProductUrl;

    @NotBlank
    private String productName;

    private String linkOnProductUrl;

    private Double productPrice;

    @Positive
    private Integer announcementDuration;

    @Positive
    private Integer shareDuration;

    @Positive
    private Integer afterShareDuration;

    @NotBlank
    private String color;

    @Positive
    private Integer pickedItemsCount;

    @Positive
    private Integer allItemsCount;

    @NotBlank
    private String codeForWinner;

    @FutureOrPresent
    private Timestamp date;

    private ShareState cellType;

    private Integer companyId;

    private AddressDto placeAddress;

    @Valid
    @NotEmpty
    private List<PickedItemDto> items;


}

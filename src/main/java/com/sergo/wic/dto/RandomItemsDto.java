package com.sergo.wic.dto;

import com.sergo.wic.dto.Response.ResponseContent;

import java.util.List;

public class RandomItemsDto extends ResponseContent {
   private List<ItemDto> items;

   public RandomItemsDto(List<ItemDto> items) {
      this.items = items;
   }
   public RandomItemsDto(){}
   public List<ItemDto> getItems() {
      return items;
   }

   public void setItems(List<ItemDto> items) {
      this.items = items;
   }
}

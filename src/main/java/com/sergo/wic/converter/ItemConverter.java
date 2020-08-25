package com.sergo.wic.converter;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ItemConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShareService shareService;


    public Item convertToModel(final PickedItemDto source) {
        Optional<Share> share = shareService.findByShareId(source.getShareId());
        if (share.isPresent()){
        Item item = new Item();
        item.setShare(share.get());
          item.setItemId(source.getItemId());
          item.setLatitude(source.getPoint().getLatitude());
          item.setLongitude(source.getPoint().getLongitude());
          return item;
        }
       //     share.ifPresent((sh) -> item.setShare(sh));
        return null;
    }

    public PickedItemDto convertToPickedItemsDto(final Item source) {
        final PickedItemDto pickedItemDto = new PickedItemDto();
        modelMapper.map(source, pickedItemDto);
        return pickedItemDto;
    }

    public List<Item> convertAllDtos(List<ItemDto> dtos){
        List<Item> result = new ArrayList<>();
        dtos.stream().forEach(((itemDto) -> modelMapper.map(itemDto,result.add(new Item()))));
        return result;
    }

    public List<ItemDto> convertAllItems(List<Item> items){
        List<ItemDto> result = new ArrayList<>(items.size());
        for(int i = 0; i < items.size(); i ++){
            result.add(new ItemDto());
            modelMapper.map(items.get(i),result.get(i));
        }
        return result;
    }
//    public Response convertToItemResponse(final Item source){
//
//    }
}

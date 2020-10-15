package com.sergo.wic.converter;

import com.sergo.wic.dto.*;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.enums.ItemState;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TypeMap<ItemDto,Item> typeMap;


//    public Item convertToModel(final PickedItemDto source) {
//        Optional<Share> share = shareService.findByShareId(source.getShareId());
//        if (share.isPresent()){
//        Item item = new Item();
//        item.setShare(share.get());
//          item.setItemId(source.getItemId());
//          item.setLatitude(source.getPoint().getLatitude());
//          item.setLongitude(source.getPoint().getLongitude());
//          return item;
//        }
//       //     share.ifPresent((sh) -> item.setShare(sh));
//        return null;
//    }

    public PickedItemDto convertToPickedItemsDto(final Item source) {
        final PickedItemDto pickedItemDto = new PickedItemDto();
        modelMapper.map(source, pickedItemDto);
        return pickedItemDto;
    }

    public List<Item> convertAllDtos(List<ItemDto> dtos){
        List<Item> result = new ArrayList<>();
        dtos.forEach( itemDto -> {
            Item item = new Item();
            item.setState(ItemState.FREE);
//            modelMapper.typeMap(ItemDto.class, Item.class)
//                    .addMappings(mapper -> mapper.map(ItemDto::getItemId,Item::setItemId));
//            modelMapper.map(itemDto,item);
//            modelMapper.validate();
            item.setItemId(itemDto.getItemId());
            item.setLongitude(itemDto.getCoordinates().getLongitude());
            item.setLatitude(itemDto.getCoordinates().getLatitude());
            result.add(item);

        });
        return result;
    }

    public List<ItemDto> convertAllItems(List<Item> items){
        List<ItemDto> result = new ArrayList<>(items.size());
        for(int i = 0; i < items.size(); i ++){
            ItemDto itemDto = new ItemDto();
            CoordinatesDto coordinatesDto = new CoordinatesDto(items.get(i).getLatitude(),items.get(i).getLongitude());
            itemDto.setCoordinates(coordinatesDto);
            result.add(itemDto);
            modelMapper.map(items.get(i),result.get(i));
        }
        return result;
    }
//    public Response convertToItemResponse(final Item source){
//
//    }
}

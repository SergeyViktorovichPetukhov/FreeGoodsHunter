package com.sergo.wic.converter;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.service.ShareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShareService shareService;

    public Item convertToModel(final ItemDto source) {
        final Item item = new Item();
          item.setLatitude(source.getPoint().getLatitude());
          item.setLongitude(source.getPoint().getLongitude());
            Optional<Share> share = shareService.findByShareId(source.getShareId());
            share.ifPresent((sh) -> item.setShare(sh));
        return item;
    }

    public ItemDto convertToDto(final Item source) {
        final ItemDto itemDto = new ItemDto();
        modelMapper.map(source, itemDto);
        return itemDto;
    }
//    public Response convertToItemResponse(final Item source){
//
//    }
}

package com.sergo.wic.converter;

import com.sergo.wic.dto.entity.ItemDto;
import com.sergo.wic.entities.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Item convertToModel(final ItemDto source) {
        final Item item = new Item();
        modelMapper.map(source, item);
        return item;
    }

    private ItemDto convertToDto(final Item source) {
        final ItemDto itemDto = new ItemDto();
        modelMapper.map(source, itemDto);
        return itemDto;
    }
}

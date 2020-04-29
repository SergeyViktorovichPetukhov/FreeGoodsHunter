package com.sergo.wic.converter;

import com.sergo.wic.dto.*;
import com.sergo.wic.dto.Response.GetShareResponse;
import com.sergo.wic.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class ShareConverter {

    private static final String PRODUCT_PHOTO_PATH = "product.photo.path";

    @Autowired
    private Environment env;

    private ModelMapper modelMapper;
    @Autowired
    private UserConverter userConverter;

    public ShareConverter( @Autowired ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    private ItemConverter itemConverter;

    public Share convertToModel(final ShareDto source) {
        final Share share = new Share();
        modelMapper.map(source, share);
        return share;
    }

    public Share convertToModel(final CreateShareDto source) {
        final Share share = new Share();
        modelMapper.map(source, share);

            List<CreateItemDto> itemDtos = source.getItems();
        Iterator<CreateItemDto> itemDtoIterator = itemDtos.iterator();
            List<Item> items = new ArrayList<>();
        Iterator<Item> itemIterator = items.iterator();
            List<ShareItems> shareItems = new ArrayList<>();
        Iterator<ShareItems> shareItemsIterator = shareItems.iterator();
//        itemDtoIterator.forEachRemaining((itemDto -> modelMapper.map(itemDto,itemIterator.next())));
//        itemIterator.forEachRemaining(item -> modelMapper.map(item,shareItemsIterator.next()));
        while (itemDtoIterator.hasNext() && itemIterator.hasNext() && shareItemsIterator.hasNext())
        {
            Item item = new Item();
            modelMapper.map(itemDtoIterator.next(),item);
            ShareItems shareItem = new ShareItems();
            modelMapper.map(itemDtoIterator.next(),shareItem);
            shareItems.add(shareItem);
            for(ShareItems shareItems1 : shareItems){
                System.out.println(shareItems1.getItem().getLatitude() + " lat of item, shareId : " + shareItems1.getShare().getShareId());
            }
        }

        share.setShareItems(shareItems);
        share.setDate(new Timestamp(System.currentTimeMillis()));
        return share;
    }

    public ShareDto convertToDto(final Share source) {
        final ShareDto shareDto = new ShareDto();
        modelMapper.map(source, shareDto);
        shareDto.setPhotoProductUrl(Objects.nonNull(source.getProductImageId()) ?
                env.getProperty(PRODUCT_PHOTO_PATH) + source.getProductImageId() :
                null);
        return shareDto;
    }

    public GetShareResponse convertToShareResponse(final Share source){
        final GetShareResponse response = new GetShareResponse();
        modelMapper.map(source, response);
        List<ShareItems> shareItems = source.getShareItems();
        List<Item> items = new ArrayList<>();
        shareItems.stream().forEach((shareItem) -> items.add(shareItem.getItem()));
        List<ItemDto> itemDtos = Arrays.asList(modelMapper.map(items,ItemDto[].class));
        response.setPoints(itemDtos);
        return response;
    }
}

package com.sergo.wic.converter;

import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.Response.GetShareResponse;
import com.sergo.wic.dto.PickedItemDto;
import com.sergo.wic.dto.ShareDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
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
    private TypeMap<ItemDto, Item> typeMapItem;
    private TypeMap<ShareDto, Share> typeMapShare;
    private ItemConverter itemConverter;

    public ShareConverter(@Autowired ModelMapper modelMapper,
                          @Autowired TypeMap<ItemDto, Item> typeMapItem,
                          @Autowired TypeMap<ShareDto, Share> typeMapShare,
                          @Autowired ItemConverter itemConverter){
        this.typeMapItem = typeMapItem;
        this.typeMapShare = typeMapShare;
        this.modelMapper = modelMapper;
        this.itemConverter = itemConverter;
    }

    public Share convertToModel(ShareDto source) {
        final Share share = new Share();
        modelMapper.map(source, share);
        return share;
    }

    public Share convertToModel(CreateShareDto source) {
        Share share = new Share();
        share.setItems(itemConverter.convertAllDtos(source.getItems()));
        share.setDate(new Timestamp(System.currentTimeMillis()));
//        modelMapper.typeMap(CreateShareDto.class, Share.class)
//                .addMappings(mapper -> mapper.map(CreateShareDto::getItems,Share::setItems));
//        modelMapper.map(source, share);
//        modelMapper.validate();
        share.setProductPrice(source.getProductPrice());
        share.setColor(source.getColor());
        share.setAfterShareDuration(source.getAfterShareDuration());
        share.setAnnouncementDuration(source.getAnnouncementDuration());
        share.setPlaceCity(source.getPlaceCity());
        share.setPlaceCountry(source.getPlaceCountry());
        share.setPlaceRegion(source.getPlaceRegion());
        share.setShareDuration(source.getShareDuration());
        share.setAnnouncementDuration(source.getAnnouncementDuration());
        share.setLinkOnProduct(source.getLinkOnProductUrl());
        share.setLogin(source.getLogin());
        share.setProductDescription(source.getDescription());
        return share;
    }

    public ShareDto convertToDto(Share source) {
        ShareDto shareDto = new ShareDto();
        modelMapper.map(source, shareDto);
        shareDto.setPhotoProductUrl(Objects.nonNull(source.getProductImageId()) ?
                env.getProperty(PRODUCT_PHOTO_PATH) + source.getProductImageId() :
                null);
        return shareDto;
    }

    public GetShareResponse convertToShareResponse(Share source){
        final GetShareResponse response = new GetShareResponse();
        modelMapper.map(source, response);
            List<Item> items = source.getItems();
            List<PickedItemDto> pickedItemDtos = Arrays.asList(modelMapper.map(items, PickedItemDto[].class));
//                List<User> users = source.getUsers();
//                List<UserDto> userDtoList = Arrays.asList(modelMapper.map(users, UserDto[].class));
            response.setPoints(pickedItemDtos);
//            response.setUsersWithShareItems(userDtoList);
        return response;
    }
}

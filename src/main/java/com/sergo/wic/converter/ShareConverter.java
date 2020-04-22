package com.sergo.wic.converter;

import com.sergo.wic.dto.UserDto;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.dto.Response.GetShareResponse;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.dto.ShareDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
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
        Share share = new Share();
        modelMapper.map(source, share);
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
            List<Item> items = source.getItems();
            List<ItemDto> itemDtos = Arrays.asList(modelMapper.map(items,ItemDto[].class));
                List<User> users = source.getUsers();
                List<UserDto> userDtoList = Arrays.asList(modelMapper.map(users, UserDto[].class));
            response.setPoints(itemDtos);
            response.setUsersWithShareItems(userDtoList);
        return response;
    }
}

package com.sergo.wic.converter;

import com.sergo.wic.dto.entity.CreateShareDto;
import com.sergo.wic.dto.entity.ShareDto;
import com.sergo.wic.entities.Share;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class ShareConverter {

    private static final String PRODUCT_PHOTO_PATH = "product.photo.path";

    @Autowired
    private Environment env;
    @Autowired
    private ModelMapper modelMapper;
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
}

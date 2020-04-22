package com.sergo.wic.facade.impl;

import com.sergo.wic.dto.ImageDto;
import com.sergo.wic.entities.Image;
import com.sergo.wic.facade.ImageFacade;
import com.sergo.wic.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageFacadeImpl implements ImageFacade {

    @Autowired
    private ImageService imageService;

    @Override
    public ImageDto getImageById(long imageId) {
        final Image image = imageService.getImageById(imageId);
        return new ImageDto(image.getId(), image.getFormat(), image.getImage());
    }
}

package com.sergo.wic.facade;

import com.sergo.wic.dto.entity.ImageDto;

public interface ImageFacade {
    ImageDto getImageById(long imageId);
}

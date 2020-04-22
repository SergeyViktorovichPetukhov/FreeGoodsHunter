package com.sergo.wic.facade;

import com.sergo.wic.dto.ImageDto;

public interface ImageFacade {
    ImageDto getImageById(long imageId);
}

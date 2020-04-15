package com.sergo.wic.service;

import com.sergo.wic.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    long saveFile(MultipartFile image) throws IOException;
    long saveFile(MultipartFile image, long imageId) throws IOException;
    Image getImageById(long imageId);
}

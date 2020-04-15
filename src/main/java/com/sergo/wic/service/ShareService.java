package com.sergo.wic.service;

import com.sergo.wic.entities.Share;
import org.springframework.web.multipart.MultipartFile;

public interface ShareService {
    Share findById(long id);
    Share saveShare(Share share);
    Share savePhotoForShareProduct(MultipartFile photo, Long shareId);
}

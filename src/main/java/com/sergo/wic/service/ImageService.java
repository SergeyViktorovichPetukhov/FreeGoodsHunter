package com.sergo.wic.service;

import com.sergo.wic.entities.Image;
import com.sergo.wic.exception.ImageNotUploadedException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveUserPhoto(MultipartFile image, String userLogin) throws ImageNotUploadedException;
    String saveProductPhoto(MultipartFile image, String userLogin, String productName) throws ImageNotUploadedException;
    String saveCompanyLogo(MultipartFile image, String userLogin, String companyLogo) throws ImageNotUploadedException;
    String getImageURL(String userLogin);
    String getImageURL(String userLogin, String productName);
    String getImageURL(String userLogin, String companyLogo, boolean isLogo);
}

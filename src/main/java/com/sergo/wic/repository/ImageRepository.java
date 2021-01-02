package com.sergo.wic.repository;

import com.sergo.wic.exception.ImageNotUploadedException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageRepository {
    String saveUserPhoto(MultipartFile image, String userLogin) throws ImageNotUploadedException;
    String saveProductPhoto(MultipartFile image, String productName, String userLogin ) throws ImageNotUploadedException;
    String saveCompanyLogo(MultipartFile image, String companyLogo, String userLogin) throws ImageNotUploadedException;
    String getImageURL(String userLogin);
    String getImageURL(String userLogin, String productName);
    String getImageURL(String userLogin, String companyLogo, boolean isLogo);
}

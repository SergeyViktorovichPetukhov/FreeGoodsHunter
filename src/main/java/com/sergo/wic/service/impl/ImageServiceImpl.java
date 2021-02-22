package com.sergo.wic.service.impl;

import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.repository.ImageRepository;
import com.sergo.wic.service.ImageService;
import com.sergo.wic.utils.PhotoPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOG = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageRepository imageRepository;

//    @Override
//    public String saveFile(final MultipartFile image, final String imageDir, final String fileName) {
//        Preconditions.checkNotNull(image, "Image can't be null!");
//        Preconditions.checkNotNull(imageDir, "Name of directory can't be null!");
//        Preconditions.checkNotNull(fileName, "File name can't be null!");
//
//        new File(imageDir).mkdir();
//        final Path filePath = Paths.get(imageDir, fileName);
//
//        try {
//            Files.write(filePath, image.getBytes());
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//        LOG.info("File " + fileName + " successfully saved !");
//
//        return filePath.toString();
//    }


    @Override
    public String saveUserPhoto(MultipartFile image, String userLogin) throws ImageNotUploadedException {
        return imageRepository.saveUserPhoto(image,userLogin);
    }

    @Override
    public String saveProductPhoto(MultipartFile image, String productName, String userLogin) throws ImageNotUploadedException {
        return imageRepository.saveProductPhoto(image,productName,userLogin);
    }

    @Override
    public String saveCompanyLogo(MultipartFile image, String companyLogo, String userLogin) throws ImageNotUploadedException {
        return imageRepository.saveCompanyLogo(image,companyLogo,userLogin);
    }

    @Override
    public String getImageURL(String userLogin) {
        return PhotoPaths.USER + userLogin;
    }

    @Override
    public String getImageURL(String userLogin, String productName) {
        return PhotoPaths.PRODUCT + userLogin +"\\" + productName;
    }

    @Override
    public String getImageURL(String userLogin, String companyLogo, boolean isLogo) {
        return PhotoPaths.COMPANY_LOGO + userLogin + "\\" + companyLogo;
    }
}

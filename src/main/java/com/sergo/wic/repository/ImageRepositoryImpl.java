package com.sergo.wic.repository;

import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.utils.PhotoPaths;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class ImageRepositoryImpl implements ImageRepository {


    @Override
    public String saveUserPhoto(MultipartFile image, String userLogin)
            throws ImageNotUploadedException {
        return saveImage(PhotoPaths.USER, image,null, userLogin);
    }

    @Override
    public String saveProductPhoto(MultipartFile image, String productName, String userLogin)
            throws ImageNotUploadedException {
        return saveImage(PhotoPaths.PRODUCT, image, productName, userLogin);
    }

    @Override
    public String saveCompanyLogo(MultipartFile image, String companyLogo, String userLogin)
            throws ImageNotUploadedException {
        return saveImage(PhotoPaths.COMPANY_LOGO, image, companyLogo, userLogin);
    }

    @Override
    public String getImageURL(String userLogin) {
        return null;
    }

    @Override
    public String getImageURL(String userLogin, String productName) {
        return null;
    }

    @Override
    public String getImageURL(String userLogin, String companyLogo, boolean isLogo) {
        return null;
    }

    private String saveImage(PhotoPaths path, MultipartFile file, String imageName, String userLogin)
                            throws ImageNotUploadedException {
        try {
            Path newFile = Paths.get(path.getValue() + userLogin + "\\" + imageName);
            if (!Files.exists(Paths.get(path.getValue() + userLogin + "\\" + imageName))){
                Files.createDirectories(Paths.get(path.getValue() + userLogin));
                Files.createFile(newFile);
                file.transferTo(newFile);
            }
            else Files.write(newFile, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageNotUploadedException(e.getMessage());
        }
        return path.toString();
    }
}

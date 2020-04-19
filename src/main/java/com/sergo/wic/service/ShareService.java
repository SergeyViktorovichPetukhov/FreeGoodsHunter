package com.sergo.wic.service;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShareService {
    Share findById(long id);
    List<Share> findAll();
    Share saveShare(Share share);
    boolean deleteShare(String shareId);
    void deleteById(Long id);
    boolean confirmShare(Long id);
    boolean cancelShare(Long id ,String reason);
    Share savePhotoForShareProduct(MultipartFile photo, Long shareId);
    List<Share> findAllByCompany(Company company);
}

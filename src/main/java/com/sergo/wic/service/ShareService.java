package com.sergo.wic.service;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ShareService {
    Share findById(long id);
    Share findByLogin(String login);
    boolean checkLoginAndShare(String login, String share);
    Optional<Share> findByShareId(String shareId);
    List<Share> findAll();
    Share saveShare(Share share);
    boolean deleteShare(String shareId);
    void deleteById(Long id);
    boolean confirmShare(Long id);
    boolean cancelShare(Long id ,String reason);
    Share savePhotoForShareProduct(MultipartFile photo, Long shareId);
    List<Share> findAllByCompany(Company company);
}

package com.sergo.wic.service;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.exception.NoSuchUserException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ShareService {
    Share findById(long id);
    Share findByLogin(String login);
    boolean checkShare( String shareId);
    boolean existsByShareId(String shareId);
    Optional<Share> findByShareId(String shareId);
    Optional<Share> findShareWithCompany(String shareId);
    List<Item> findShareUserItems(String shareId);
    List<Share> findAll();
    List<Share> findNewShares(Long maxOldId,Long companyId);
    Share saveShare(Share share, MultipartFile productPhoto) throws IOException, NoSuchUserException;
    boolean deleteShare(String shareId);
    void deleteById(Long id);
    boolean confirmShare(Long id, String reason);
    boolean cancelShare(Long id ,String reason);
    Share savePhotoForShareProduct(MultipartFile photo, String shareId,
                                   String productName, String userLogin) throws ImageNotUploadedException;
    List<Share> findAllByCompany(Company company);
    List<Share> findAllByRegionCode(String regionCode);
    String getRegionCode(String country, String region, String city);
}

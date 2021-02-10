package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.enums.CreateShareState;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.exception.NoSuchUserException;
import com.sergo.wic.repository.ItemRepository;
import com.sergo.wic.repository.PointsRepository;
import com.sergo.wic.repository.ShareRepository;
import com.sergo.wic.service.*;
import com.sergo.wic.utils.RandomString;
import org.postgis.Geometry;
import org.postgis.GeometryBuilder;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Service("shareService")
public class ShareServiceImpl implements ShareService {

    private static final String PHOTO_PATH = "/D:/photoFGH";
    private static final String SERVER_PHOTO_PATH = "/photoFGH";

    public ShareServiceImpl(
            @Autowired ShareRepository shareRepository,
            @Autowired ImageService imageService,
            @Autowired UserService userService ){
       this.imageService = imageService;
       this.shareRepository = shareRepository;
       this.userService = userService;
    }

    private ShareRepository shareRepository;
    private ImageService imageService;
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(ShareServiceImpl.class);


    @Override
    public Share findById(final long id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public List<Share> findAll(){
        return shareRepository.findAll();
    }

    @Override
    @Transactional
    public Share saveShare(Share share, MultipartFile productPhoto)
            throws ImageNotUploadedException, NoSuchUserException {

        Optional<User> user = userService.findByLogin(share.getLogin());
        if (user.isPresent()){
            String shareId = user.get().getLogin() + " " + RandomString.getAlphaNumericString(6);
            String photoUrl = imageService.saveProductPhoto(
                    productPhoto,
                    user.get().getLogin(),
                    share.getProductName()
            );
            share.setShareId(shareId);
            share.setProductPhotoUrl(photoUrl);
            share.setCompany(user.get().getCompany());
            share.setCreationStatus(CreateShareState.CREATED);
            shareRepository.save(share);
        //    itemRepository.saveAll(share.getItems());
        //    companyService.save(user.get().getCompany());
            return share;
        }
        else{
            throw new NoSuchUserException("no such user");
        }
    }

    @Override
    public Optional<Share> findByShareId(String shareId) {
        return shareRepository.findByShareId(shareId);
    }

    @Override
    public Optional<Share> findShareWithCompany(String shareId) {
        return shareRepository.findShareWithCompany(shareId);
    }

    @Override
    public List<Item> findShareUserItems(String shareId) {
        return shareRepository.findShareUserItems(shareId);
    }

    @Override
    @Transactional
    public boolean deleteShare(String shareId) {
        Share share = shareRepository.findByShareId(shareId).get();
        if (share != null){
            shareRepository.deleteById(share.getId());
            return true;
        }
        return false;
    }

    @Override
    public Share findByLogin(String login) {
        Optional<Share> share = shareRepository.findByLogin(login);
        return  share.orElseGet(null);
    }

    @Override
    public boolean checkShare(String shareId) {
        return shareRepository.existsByShareId(shareId);
    }

    @Override
    public boolean existsByShareId(String shareId){
        return shareRepository.existsByShareId(shareId);
    }

    @Override
    public List<Share> findAllByCompany(Company company) {
        return shareRepository.findAllByCompany(company);
    }

    @Override
    public void deleteById(Long id) {
        shareRepository.deleteById(id);
    }

    @Override
    public boolean confirmShare(Long id, String reason) {
        Share share = findById(id);
        share.setCreationStatus(CreateShareState.CONFIRMED);
        share.setMessageForUser(reason);
        shareRepository.save(share);
        return true;
    }

    @Override
    public boolean cancelShare(Long id , String reason) {
        Share share = findById(id);
        share.setCreationStatus(CreateShareState.REFUSED);
        share.setMessageForUser(reason);
        shareRepository.save(share);
        return true;
    }

    @Override
    public List<Share> findNewShares(Long maxOldId,Long companyId) {
        Optional<List<Share>> newShares =  shareRepository.findNewShares(maxOldId);
        if (newShares.isPresent()){
        for(Share share : newShares.get()){
            System.out.println(share.getId() + " new share");
        }
            System.out.println();
            return newShares.get();
        }
        return null;
    }


    @Override
    public Share savePhotoForShareProduct(MultipartFile photo,  String shareId,
                                          String productName, String userLogin) throws ImageNotUploadedException {

        Share share = shareRepository.findByShareId(shareId)
                .orElseThrow(() -> new IllegalStateException("Share with id: " + shareId + " doesn't exist !"));
        try {
            String productPhotoUrl;
            if (Objects.isNull(share.getProductPhotoUrl())) {
                productPhotoUrl = imageService.saveProductPhoto(photo, productName, userLogin);
                share.setProductPhotoUrl(productPhotoUrl);
                shareRepository.save(share);
            } else {
                productPhotoUrl = imageService.saveProductPhoto(photo, productName, userLogin);
            }
            LOG.info("Photo " + productPhotoUrl + " successfully set to product of share:  " + shareId);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new ImageNotUploadedException(e.getMessage());
        }

        return share;
    }

    private String getShareId(User user, Share share){
        return user.getLogin() + " "
                + LocalDate.now().toString() + " "
                + user.getSharesCount(share) + " ,"
                + RandomString.getAlphaNumericString(3);
    }

    private boolean addItemsBufferZone(List<Item> items){
        List<PGgeometry> points = new ArrayList<>();
        items.forEach(item -> {
            PGgeometry point = new PGgeometry();
            point.setGeometry(new Point(item.getLongitude(),item.getLatitude()));
            points.add(point);
        });
        return true;
    }

    @Override
    public List<Share> findAllByRegionCode(String regionCode) {
        return shareRepository.findAllByRegionCode(regionCode).orElse(null);
    }

    @Override
    public String getRegionCode(String country, String region, String city) {
        return country.substring(0,3) + region.substring(0,3) + city;
    }
}

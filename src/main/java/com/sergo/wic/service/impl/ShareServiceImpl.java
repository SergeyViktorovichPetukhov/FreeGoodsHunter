package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.CreateShareState;
import com.sergo.wic.entities.Share;
import com.sergo.wic.repository.ShareRepository;
import com.sergo.wic.service.ImageService;
import com.sergo.wic.service.ShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service("shareService")
public class ShareServiceImpl implements ShareService {


    public ShareServiceImpl(@Autowired Environment env, @Autowired
    ShareRepository shareRepository, @Autowired ImageService imageService){
       this.env = env;
       this.imageService = imageService;
       this.shareRepository = shareRepository;
    }

    private Environment env;
    private ShareRepository shareRepository;
    private ImageService imageService;

    private static final String PRODUCT_PHOTO_PATH = "product.photo.path";
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
    public Share saveShare(final Share share) {
        return shareRepository.save(share);
    }

    @Override
    @Transactional
    public boolean deleteShare(String shareId) {
        Share share = shareRepository.findByShareId(shareId);
        if (share != null){
            shareRepository.deleteById(share.getId());
            return true;
        }
        return false;
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
    public boolean confirmShare(Long id) {
        Share share = findById(id);
        share.setCreateStatus(CreateShareState.CONFIRMED);
        share.setMessageForUser("Спасибо за публикацию акции. Вы делаете всех нас лучше!");
        shareRepository.save(share);
        return true;
    }

    @Override
    public boolean cancelShare(Long id , String reason) {
        Share share = findById(id);
        share.setCreateStatus(CreateShareState.REFUSED);
        share.setMessageForUser(reason);
        shareRepository.save(share);
        return true;
    }

    //    @Override
//    public ShareModel savePhotoForShareProduct(final MultipartFile photo, final Long shareId) {
//        final String productPhotoDir = env.getProperty(PRODUCT_PHOTO_PATH) + shareId;
//        final ShareModel share = shareRepository.findById(shareId)
//                                          .orElseThrow(() -> new IllegalStateException(
//                                                  "Share with id: " + shareId + " doesn't exist !"));
//        final String productName = share.getProductName();
//        Preconditions.checkNotNull(productName, "Product name can't be null!");
//        final String pathToPhoto = imageService.saveFile(photo, productPhotoDir, productName);
//        share.setProductImageId(pathToPhoto);
//
//        LOG.info("Photo " + pathToPhoto + " successfully set to product of share:  " + shareId);
//
//        return share;
//    }

    @Override
    public Share savePhotoForShareProduct(final MultipartFile photo, final Long shareId) {
        final Share share = shareRepository.findById(shareId)
                                                .orElseThrow(() -> new IllegalStateException(
                                                      "Share with id: " + shareId + " doesn't exist !"));
        try {
            final long imageId;
            if (Objects.isNull(share.getProductImageId())) {
                imageId = imageService.saveFile(photo);
                share.setProductImageId(imageId);
                shareRepository.save(share);
            } else {

                imageId = imageService.saveFile(photo, share.getProductImageId());

            }
            LOG.info("Photo " + imageId + " successfully set to product of share:  " + shareId);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return share;
    }

}

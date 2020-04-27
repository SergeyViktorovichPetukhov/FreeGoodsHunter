package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.CreateShareState;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.repository.ShareRepository;
import com.sergo.wic.service.ImageService;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.service.UserService;
import com.sergo.wic.utils.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service("shareService")
public class ShareServiceImpl implements ShareService {

    private static final String PHOTO_PATH = "/D:/photoFGH";
    private static final String SERVER_PHOTO_PATH = "/photoFGH";

    public ShareServiceImpl(@Autowired Environment env, @Autowired
    ShareRepository shareRepository, @Autowired ImageService imageService,
     @Autowired UserService userService){
       this.env = env;
       this.imageService = imageService;
       this.shareRepository = shareRepository;
       this.userService = userService;
  //     this.itemService = itemService;
    }

    private Environment env;
    private ShareRepository shareRepository;
    private ImageService imageService;
    private UserService userService;
   // private ItemService itemService;

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
    public Share saveShare(final Share share, final MultipartFile productPhoto) throws IOException {

        Optional<User> userOptional = userService.findByLogin(share.getLogin());

        if (!productPhoto.isEmpty() && userOptional.isPresent()){
            User user = userOptional.get();

            UUID uuid = new UUID(10*100,26);

           // File userPhotoPath = new File(PHOTO_PATH + "/" + user.getLogin());
            File userPhotoPath = new File(SERVER_PHOTO_PATH + "/" + user.getLogin());
            String photoUrl = userPhotoPath.toString() + " " + uuid.toString();

            String shareId = user.getEmail() + " "
                    + LocalDate.now().toString() + " #"
                    + user.getSharesCount(share) + " ,"
                    + RandomString.getAlphaNumericString(3);
            if (existsByShareId(shareId)) {
                throw new IOException("share already exists");
            }

            share.setShareId(shareId);
            byte[] bytes = productPhoto.getBytes();
            FileOutputStream outputStream = null;
//            try {
                outputStream = new FileOutputStream(photoUrl + productPhoto.getOriginalFilename().substring(productPhoto.getOriginalFilename().lastIndexOf(".")));
                outputStream.write(bytes);
//            }catch (IOException e){
//                throw new IOException("could not upload image");
//            }

            share.setProductPhotoUrl(photoUrl);
            return shareRepository.save(share);
        }
        else{
            throw new IOException("could not load photo, try again");
        }
    }

    @Override
    public Share saveShare1(final Share share) throws IOException {

        Optional<User> userOptional = userService.findByLogin(share.getLogin());

        if (userOptional.isPresent()){
            User user = userOptional.get();
            String shareId = user.getEmail() + " "
                    + LocalDate.now().toString() + " #"
                    + user.getSharesCount(share);
            if (existsByShareId(shareId)) {
                throw new IOException("share already exists");
            }
            share.setShareId(shareId);

        }
        return shareRepository.save(share);
    }

    @Override
    public Optional<Share> findByShareId(String shareId) {
        return shareRepository.findByShareId(shareId);
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

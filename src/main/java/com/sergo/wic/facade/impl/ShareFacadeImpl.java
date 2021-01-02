package com.sergo.wic.facade.impl;

import com.sergo.wic.converter.ShareConverter;
import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.SharesResponse;
import com.sergo.wic.dto.ShortShareInfoDto;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.dto.ShareDto;
import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.enums.ShareState;
import com.sergo.wic.entities.User;
import com.sergo.wic.exception.ImageNotUploadedException;
import com.sergo.wic.facade.ShareFacade;
//import com.sergo.wic.repository.AddressRepository;
import com.sergo.wic.repository.ItemRepository;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.ShareService;
import com.sergo.wic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class ShareFacadeImpl implements ShareFacade {

    private static final String IMAGE_UPLOAD_BASE_PATH = "upload.images.path";
    private static final String PRODUCT_PHOTO_PATH = "product.photo.path";

    private static final Logger LOG = LoggerFactory.getLogger(ShareFacadeImpl.class);

    @Autowired
    private ShareService shareService;

    @Autowired
    private ShareConverter shareConverter;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Override
    public ShareDto saveShare(CreateShareDto createShareDto) {

        User user = userService.findByLogin(createShareDto.getLogin()).get();
        Company company = user.getCompany();
        Share share = shareConverter.convertToModel(createShareDto,company);
        for(Share share1 : company.getShares()){
            System.out.println(share1.getDate() + " share get date");
        }
        share.setShareId(user.getLogin() + " "
                       + LocalDate.now().toString() + " #"
                       + user.getSharesCount(share));

        company.getShares().add(share);
        share.setCompany(company);
    //    shareService.saveShare(share,null);
        share.getItems().forEach(item -> {
            item.setShare(share);
            itemRepository.save(item);
        });
        return shareConverter.convertToDto(share);
    }

    @Override
    public Response deleteShare(String shareId) {
        if (shareService.deleteShare(shareId)){
            return new Response(true,0);
        }
        else return new Response(false,1,"no such share");
    }

    @Override
    public ShareDto uploadPhotoForShareProduct(MultipartFile photo, String shareId, String userLogin, String productName)
                                                throws ImageNotUploadedException {
        return shareConverter.convertToDto(shareService.savePhotoForShareProduct(photo, shareId,userLogin, productName));
    }

    public SharesResponse getShares(String login, String country, String region,String city) {
        return getShareRequestTestData(isValidGetShareRequest(login, country, region, city));
    }

    private Boolean isValidGetShareRequest(String login, String country, String region, String city) {
        return !StringUtils.isEmpty(login) && !StringUtils.isEmpty(country) &&
                !StringUtils.isEmpty(region) && !StringUtils.isEmpty(city) &&
                "user@gmail.com".equals(login) && ("all".equals(country) || "Ukraine".equals(country)) &&
                ("all".equals(region) || "Kiev reg".equals(region)) && ("kiev".equals(city) || "all".equals(city));
    }

    private SharesResponse getShareRequestTestData(final Boolean valid) {
        SharesResponse sharesResponse;
        if (valid) {
            ShortShareInfoDto mcdonaldShare = new ShortShareInfoDto("/images/mcdonalds-logo.png", "Macdonalds", 55,
                    10, "#FF1493", ShareState.ACTIVE.ordinal());
            ShortShareInfoDto cocaColaShare = new ShortShareInfoDto("/images/cocacola-logo.png", "Cocacola", 23,
                    0, "#FFA500", ShareState.SOON.ordinal());
            ShortShareInfoDto cocaColaShare2 = new ShortShareInfoDto("/images/cocacola-logo.png", "Cocacola", 44,
                    0, "#32CD32", ShareState.COMPLETED.ordinal());
            sharesResponse = new SharesResponse(Arrays.asList(mcdonaldShare, cocaColaShare, cocaColaShare2));
            sharesResponse.setSuccess(true);
            sharesResponse.setErrorCode(0);
        } else {
            sharesResponse = new SharesResponse();
            sharesResponse.setSuccess(false);
            sharesResponse.setErrorCode(1);
        }
        return sharesResponse;
    }

}

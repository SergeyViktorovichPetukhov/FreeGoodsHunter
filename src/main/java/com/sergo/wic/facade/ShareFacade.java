package com.sergo.wic.facade;

import com.sergo.wic.dto.SharesResponse;
import com.sergo.wic.dto.entity.CreateShareDto;
import com.sergo.wic.dto.entity.ShareDto;
import org.springframework.web.multipart.MultipartFile;

public interface ShareFacade {
    ShareDto saveShare(CreateShareDto createShareDto);
    ShareDto uploadPhotoForShareProduct(MultipartFile photo, Long shareId);
    SharesResponse getShares(String login, String country, String region, String city);
}

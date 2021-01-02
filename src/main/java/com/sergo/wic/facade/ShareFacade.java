package com.sergo.wic.facade;

import com.sergo.wic.dto.Response.Response;
import com.sergo.wic.dto.Response.SharesResponse;
import com.sergo.wic.dto.CreateShareDto;
import com.sergo.wic.dto.ShareDto;
import com.sergo.wic.exception.ImageNotUploadedException;
import org.springframework.web.multipart.MultipartFile;

public interface ShareFacade {
    ShareDto saveShare(CreateShareDto createShareDto);
    Response deleteShare(String shareId);
    ShareDto uploadPhotoForShareProduct (MultipartFile photo, String shareId, String userLogin, String productName)
            throws ImageNotUploadedException;
    SharesResponse getShares(String login, String country, String region, String city);
}

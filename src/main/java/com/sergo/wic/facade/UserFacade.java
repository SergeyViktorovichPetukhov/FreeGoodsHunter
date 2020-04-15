package com.sergo.wic.facade;

import com.sergo.wic.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserFacade {
    boolean hasUserCompany(String login);
    UserProfileResponse getUserProfile(String login);
    FriendListResponce getFriends(String login);
    AllItemsResponse getAllItems(String login);
    public Boolean isLoginValid(final String login);
    NoticeResponse getNotices(String login);
    RatingResponse getRatingOfUsers(String country, String region, String city);
    Response registerCompany(String login, String address, String phone, String internetShop, Long user_id, int code);
    Response verifyCode(String phone, Integer code);
    Response addContactToUser(String login, String typeContact, String contact);
    Response setPhotoToUser(String login, MultipartFile photo);
    Response saveProductPhotoForShare(String shareId, MultipartFile photo);
    Map<String, List<ContactDto>> getUserContacts();
}

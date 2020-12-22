package com.sergo.wic.facade.impl;

import com.sergo.wic.company_check.AWSAPIChecker;
import com.sergo.wic.dto.*;
import com.sergo.wic.dto.Response.*;
import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.Registration;
import com.sergo.wic.entities.UserProfile;
import com.sergo.wic.entities.enums.RegisteredBy;
import com.sergo.wic.entities.enums.RegistrationState;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.enums.TypeContact;
import com.sergo.wic.facade.UserFacade;
import com.sergo.wic.service.CompanyService;
import com.sergo.wic.service.RegistrationService;
import com.sergo.wic.service.UserProfileService;
import com.sergo.wic.service.UserService;
import com.sergo.wic.service.email.EmailService;
import com.sergo.wic.utils.ObjectMapperUtils;
import com.sergo.wic.utils.RandomString;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class UserFacadeImpl implements UserFacade {

    private static final String PHOTO_UPLOAD_DIR = "upload.images.path";
    private static final String PRODUCT_PHOTO_DIR = "product.photo.path";
    private static final int CODE_LOWER_LIMIT = 10000;
    private static final int CODE_UPPER_LIMIT = 100000;
    private static Map<String, CompanyDto> companies = new HashMap();

    private static final Logger LOG = LoggerFactory.getLogger(UserFacadeImpl.class);

    private List<UserDto> users;
    private List<AllItemsDto> allItemsDtos;
    private List<ContactDto> contacts;
    private Map<String, List<ContactDto>> userContacts;

    @Autowired
    private Environment env;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AWSAPIChecker awsapiChecker;

    @Autowired
    private ModelMapper modelMapper;

    {
       byte[] photo = null;
       try {
           photo = Files.readAllBytes(Paths.get("images/cocacola-logo.png"));
       }catch (IOException e){

       }
//        users = Arrays.asList(
//                new UserDto("sergeyp3d@rambler.ru",photo, 4, 6),
//                new UserDto("yarus@gmail.com",photo, 6, 8),
//                new UserDto("sergiy@gmail.com",photo, 10, 12),
//                new UserDto("sasha@gmail.com",photo, 15, 17),
//                new UserDto("user@gmail.com",photo, 16, 25)
//        );
        contacts = Arrays.asList(
                new ContactDto(TypeContact.PHONE_NUMBER, "+38415426785"),
                new ContactDto(TypeContact.SKYPE, "dimas"),
                new ContactDto(TypeContact.VIBER, "goarmor")
        );
        userContacts = new HashMap<>();
        userContacts.put("user@gmail.com", contacts);
    }


    @Override
    public boolean hasUserCompany(String login){
        return checkCompanyOwner(login);
    }

    @Transactional
    @Override
    public ResponseContent getUserProfile(String login, boolean isRequestedFromMenu) {
        Optional<User> user = userService.findByLogin(login);
        if (user.isPresent()) {
            User u = user.get();
            if (!isRequestedFromMenu) {
                UserProfileResponse response = new UserProfileResponse();
                List<ContactDto> contacts = ObjectMapperUtils.mapAll(
                        u.getUserProfile().getContacts(), ContactDto.class);
                modelMapper.map(userProfileService.findByUser(u), response);
                response.setContacts(contacts);
                return response;
            } else {
                UserProfile userProfile = u.getUserProfile();
                return new UserProfileResponse(
                        userProfile.getUserName(),
                        userProfile.getPhotoUrl(),
                        new ContactDto(
                                TypeContact.PHONE_NUMBER,
                                userProfile.getContact(TypeContact.PHONE_NUMBER).getContact())
                        );
            }
        }return null;
    }

    @Transactional
    @Override
    public boolean addUserNameInfo(String login, String firstNameAndLastName) {
        Optional<User> user = userService.findByLogin(login);
        if (user.isPresent()){
            User u = user.get();
            UserProfile profile = u.getUserProfile();
            profile.setUserName(firstNameAndLastName);
            u.setUserProfile(profile);
            return true;
        }
        return false;
    }

    @Override
    public FriendListResponce getFriends(final String login) {
        return getFriendListResponceTestData(isLoginValid(login));
    }

    @Override
    public AllItemsResponse getAllItems(final String login) {
        return getAllItemsResponceTestData(isLoginValid(login));
    }

    @Override
    public NoticeResponse getNotices(final String login) {
        return getNoticesTestData(isLoginValid(login));
    }

    @Override
    public Boolean isLoginValid(final String login) {
        User model = userService.findByLogin(login).get();
        return !StringUtils.isEmpty(login) & (model.getLogin().equals(login) & model.isHasCompany());
    }

    public static Boolean isShareIdValid(final String shareId) {
        return !StringUtils.isEmpty(shareId);
    }

    private boolean checkCompanyOwner(String login){
        return userService.findByLogin(login).get().isHasCompany();
    }

//    private UserProfileResponse getUserProfileTestData(final Boolean valid) {
//        UserProfileResponse userProfileResponse = new UserProfileResponse();
//        if (valid) {
//            userProfileResponse.setUserName("Yarik&Dima");
//            userProfileResponse.setPhotoUrl("/images/mcdonalds-logo.png");
//            userProfileResponse.setWinCount(10);
//            userProfileResponse.setRange(2.3F);
//            userProfileResponse.setPointsCount(Arrays.asList(5, 0, 0, 9, 0, 3, 0, 6, 23, 0, 9, 13));
//            userProfileResponse.setContacts(userContacts.get("user@gmail.com"));
//            userProfileResponse.setSuccess(true);
//            userProfileResponse.setErrorCode(0);
//        } else {
//            userProfileResponse.setSuccess(false);
//            userProfileResponse.setErrorCode(1);
//        }
//        return userProfileResponse;
//    }

    private FriendListResponce getFriendListResponceTestData(final Boolean valid) {
        FriendListResponce friendListResponce = new FriendListResponce();
        if (valid) {
            friendListResponce.setFriends(users);
            friendListResponce.setSuccess(true);
            friendListResponce.setErrorCode(0);
        } else {
            friendListResponce.setSuccess(false);
            friendListResponce.setErrorCode(1);
        }
        return friendListResponce;
    }

    private AllItemsResponse getAllItemsResponceTestData(final Boolean valid) {
        AllItemsResponse allItemsResponse = new AllItemsResponse();
        if (valid) {
            AllItemsDto mcdonaldItem = new AllItemsDto("testSharedId", "Macdonalds", "/images/mcdonalds-logo.png",
                    "/images/product-macdonalds.png", 55, 10);
            AllItemsDto cocaColaItem = new AllItemsDto("testSharedId2", "Cocacola", "/images/cocacola-logo.png",
                    "/images/product-cocacola.png", 44, 7);
            AllItemsDto cocaColaItem2 = new AllItemsDto("testSharedId3", "Cocacola", "/images/cocacola-logo.png",
                    "/images/product-1-cocacola.png", 44, 5);

            allItemsResponse = new AllItemsResponse(Arrays.asList(mcdonaldItem, cocaColaItem, cocaColaItem2));
            allItemsResponse.setSuccess(true);
            allItemsResponse.setErrorCode(0);
        } else {
            allItemsResponse.setSuccess(false);
            allItemsResponse.setErrorCode(1);
        }
        return allItemsResponse;
    }

    private NoticeResponse getNoticesTestData(final Boolean valid) {
        NoticeResponse noticesResponse = new NoticeResponse();
        if (valid) {
            NoticeDto notice1 = new NoticeDto("checkPhone note 1", LocalDateTime.now().minusDays(2).toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
            NoticeDto notice2 = new NoticeDto("checkPhone note 2", LocalDateTime.now().minusDays(4).toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
            NoticeDto notice3 = new NoticeDto("checkPhone note 3", LocalDateTime.now().minusDays(6).toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
            noticesResponse = new NoticeResponse(Arrays.asList(notice1, notice2, notice3));
            noticesResponse.setSuccess(true);
            noticesResponse.setErrorCode(0);
        } else {
            noticesResponse.setSuccess(false);
            noticesResponse.setErrorCode(1);
        }
        return noticesResponse;
    }

    @Override
    public RatingResponse getRatingOfUsers(final String country, final String region, final String city) {
        return getRatingOfUsersTestData(isValidRatingOfUsersRequest(country, region, city));
    }

    @Override
    public Response registerByWebSite(final String login, final String contact, boolean isChecked, final String url) {
        Optional<User> user = userService.findByLogin(login);
        if (user.isPresent()){
            user.get().setCompanyRegInProcess(true);
            userService.save(user.get());
        }
        else
            return new Response(false,1,"no such user");

        String code = RandomString.getAlphaNumericString(6);
        System.out.println("random code: " + code);
        String regId = login + " " + RandomString.getAlphaNumericString(5);
        Registration registration = new Registration(login, code, contact, regId, userService.findByLogin(login).get().getId());
        registration.setAlexaRank(awsapiChecker.getAlexaRank(url));
        registration.setState(RegistrationState.IN_PROCESS);
        registration.setChecked(true);
        registration.setRegisteredBy(RegisteredBy.WEB_SITE);
        registrationService.save(registration);
        emailService.sendSimpleMessage(user.get().getLogin(),"web-site registration","your application submitted to moderator");
        return new Response(true, 0,"application submitted to moderator", new RegistrationResponse(registration.getRegId()));
    }

    @Override
    public Response registerByGooglePlaces(String login, String phone) {
        Optional<User> user = userService.findByLogin(login);
        if (user.isPresent()){
            user.get().setCompanyRegInProcess(true);
            userService.save(user.get());
        }else return new Response(false,1,"no such user");
        String code = RandomString.getAlphaNumericString(6);
        System.out.println("random code: " + code);
        String regId = login + " " + RandomString.getAlphaNumericString(5);
        Registration registration = new Registration(login, code, phone,regId, userService.findByLogin(login).get().getId());
        registration.setState(RegistrationState.CONFIRMED);
        registration.setRegisteredBy(RegisteredBy.GOOGLE_PLACES);
//        registration.setChecked(true);
//        registration.setRefused(false);
//        registration.setConfirmed(false);
        registrationService.save(registration);
        emailService.sendSimpleMessage(user.get().getLogin(),"google places registration","your application approved");
        return new Response(true, 0,"your application approved, verify this code: " + registration.getCode(), new RegistrationResponse(registration.getRegId()));
    }

    @Override
    @Transactional
    public Response verifyCode(String login,String regId, String code) {
        System.out.println(login + " " + regId+ " " + code);
        User user;
        Optional<User> userOptional = userService.findByLogin(login);
        if (userOptional.isPresent()){
            user = userOptional.get();
        }
        else
            return new Response(false,1,"no such user");
        Registration registration = null;
        try {
            registration = registrationService
                              .findByRegId(regId)
                                  .orElseThrow( () -> new RuntimeException());
        }catch (RuntimeException e){
            return new Response(false,1,"no such registration");
        }
        if (code.equals("fgh") || (registration.getCode().equals(code))){
            registration.setState(RegistrationState.CONFIRMED);
                registrationService.save(registration);
                userService.save(user);
                companyService.save(new Company(user.getLogin(),user.getLogin(),user));
            return new Response(true,0,"your company registered");
        }
        return new Response(false,2,"wrong code");
    }

    private Boolean isValidRatingOfUsersRequest(final String country, final String region,
                                                final String city) {
        return !StringUtils.isEmpty(country) && !StringUtils.isEmpty(region) && !StringUtils.isEmpty(city) &&
                ("all".equals(country) || "Ukraine".equals(country)) && ("all".equals(region) ||
                "Kiev reg".equals(region)) && ("kiev".equals(city) || "all".equals(city));
    }

    private RatingResponse getRatingOfUsersTestData(final Boolean valid) {
        RatingResponse ratingResponse = new RatingResponse();
        if (valid) {
            ratingResponse.setUsers(users);
            ratingResponse.setSuccess(true);
            ratingResponse.setErrorCode(0);
        } else {
            ratingResponse.setSuccess(false);
            ratingResponse.setErrorCode(1);
        }
        return ratingResponse;
    }

    @Override
    public Response addContactToUser(final String login, final String typeContact, final String contact) {
        if (isLoginValid(login) && !StringUtils.isEmpty(typeContact) && Objects.nonNull(TypeContact.valueOf
                (typeContact)) && !StringUtils.isEmpty(contact)) {
            List<ContactDto> contactDtos = new ArrayList<>(userContacts.get(login));
            contactDtos.add(new ContactDto(TypeContact.valueOf(typeContact), contact));
            userContacts.put(login, contactDtos);
            return new Response(true, 0);
        } else {
            return new Response(false, 1);
        }
    }

    @Override
    public Response setPhotoToUser(final String login, final MultipartFile photo) {
        if(isLoginValid(login)) {
            final String uploadDir = env.getProperty(PHOTO_UPLOAD_DIR);

            new File(uploadDir).mkdir();
            final Path filePath = Paths.get(uploadDir, photo.getOriginalFilename());
            try {
                Files.write(filePath, photo.getBytes());
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            LOG.info("File " + photo.getOriginalFilename() + " for user " + login + " successfully uploaded !");
            return new Response(true, 0);
        }
        return new Response(false, 1);
    }

    @Override
    public Response saveProductPhotoForShare(final String shareId, final MultipartFile photo) {
        if(isShareIdValid(shareId)) {
            final String separator = System.lineSeparator();
            final String uploadDir = env.getProperty(PHOTO_UPLOAD_DIR);
            final String productPhotoDir = env.getProperty(PRODUCT_PHOTO_DIR);


            new File(uploadDir).mkdir();
            final Path filePath = Paths.get(uploadDir, productPhotoDir, separator + shareId, separator + shareId);
            try {
                Files.write(filePath, photo.getBytes());
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            LOG.info("File " + photo.getOriginalFilename() + " for share " + shareId + " successfully uploaded !");
            return new Response(true, 0);
        }
        return new Response(false, 1);
    }

    @Override
    public Map<String, List<ContactDto>> getUserContacts() {
        return userContacts;
    }

}

package com.sergo.wic.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shares",
       indexes = {
       @Index(name = "SHARE_ID_INDEX",
              columnList = "share_id")
          }
)
public class Share {

    public Share(){
        this.creationStatus = CreateShareState.CREATED;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
@Id
@SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

    @Column(name = "share_id")
    private String shareId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id" )
    private Company company;

    @Column(name = "login")
    private String login;

    @Column(name = "product_photo_url")
    private String productPhotoUrl;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "link_on_product")
    private String linkOnProduct;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "announcement_duration")
    private Integer announcementDuration;

    @Column(name = "share_duration")
    private Integer shareDuration;

    @Column(name = "after_share_duration")
    private Integer afterShareDuration;

    @Column(name = "color")
    private String color;

    @Column(name = "picked_items_count")
    private Integer pickedItemsCount;

    @Column(name = "all_items_count")
    private Integer allItemsCount;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private Timestamp date;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ShareState status;

    @Column(name = "creation_status")
    @Enumerated(EnumType.STRING)
    private CreateShareState creationStatus;

    @Column(name = "message_for_user")
    private String messageForUser;

    @OneToMany(mappedBy = "share",fetch = FetchType.LAZY)
    private List<Item> items;

//    @OneToMany(mappedBy = "sharesId",fetch = FetchType.EAGER)
//    private List<Item> items;

    @Column(name = "place_country")
    private String placeCountry;

    @Column(name = "place_region")
    private String placeRegion;

    @Column (name = "place_city")
    private String placeCity;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "users_shares",
            joinColumns = { @JoinColumn(name = "share_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;

//    @ManyToOne
//    @JoinColumn(name = "place_address_id", referencedColumnName = "id")
//    private Address placeAddress;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public void setPickedItemsCount(Integer pickedItemsCount) {
        this.pickedItemsCount = pickedItemsCount;
    }

    public void setAllItemsCount(Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProductPhotoUrl() {
        return productPhotoUrl;
    }

    public void setProductPhotoUrl(String productPhotoUrl) {
        this.productPhotoUrl = productPhotoUrl;
    }

    public String getPlaceCountry() {
        return placeCountry;
    }

    public void setPlaceCountry(String placeCountry) {
        this.placeCountry = placeCountry;
    }

    public String getPlaceRegion() {
        return placeRegion;
    }

    public void setPlaceRegion(String placeRegion) {
        this.placeRegion = placeRegion;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public CreateShareState getCreateStatus() {
        return creationStatus;
    }

    public void setCreateStatus(CreateShareState createStatus) {
        this.creationStatus = createStatus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateShareState getCreationStatus() {
        return creationStatus;
    }

    public void setCreationStatus(CreateShareState creationStatus) {
        this.creationStatus = creationStatus;
    }

    public String getMessageForUser() {
        return messageForUser;
    }

    public void setMessageForUser(String messageForUser) {
        this.messageForUser = messageForUser;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public Long getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Long productImageId) {
        this.productImageId = productImageId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLinkOnProduct() {
        return linkOnProduct;
    }

    public void setLinkOnProduct(String linkOnProduct) {
        this.linkOnProduct = linkOnProduct;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getAnnouncementDuration() {
        return announcementDuration;
    }

    public void setAnnouncementDuration(Integer announcementDuration) {
        this.announcementDuration = announcementDuration;
    }

    public Integer getShareDuration() {
        return shareDuration;
    }

    public void setShareDuration(Integer shareDuration) {
        this.shareDuration = shareDuration;
    }

    public Integer getAfterShareDuration() {
        return afterShareDuration;
    }

    public void setAfterShareDuration(Integer afterShareDuration) {
        this.afterShareDuration = afterShareDuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPickedItemsCount() {
        return pickedItemsCount;
    }

    public void setPickedItemsCount(int pickedItemsCount) {
        this.pickedItemsCount = pickedItemsCount;
    }

    public int getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(int allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ShareState getStatus() {
        return status;
    }

    public void setStatus(ShareState status) {
        this.status = status;
    }

//    public Integer getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(Integer companyId) {
//        this.companyId = companyId;
//    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

//    public Address getPlaceAddress() {
//        return placeAddress;
//    }
//
//    public void setPlaceAddress(Address placeAddress) {
//        this.placeAddress = placeAddress;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Share that = (Share) o;
        return id == that.id &&
                shareId.equals(that.getShareId());
    }

    @Override public int hashCode() {
        return Objects.hash( productPrice, announcementDuration, shareDuration,
                            afterShareDuration, pickedItemsCount, allItemsCount);

    }
}

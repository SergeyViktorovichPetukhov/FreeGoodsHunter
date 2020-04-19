package com.sergo.wic.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shares")
public class Share {

    public Share(){
        this.creationStatus = CreateShareState.CREATED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "share_id")
    private String shareId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id" )
    private Company company;

    @Column(name = "login")
    private String login;

    @Column(name = "product_photo")
    private String productPhoto;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "count_of_product")
    private int countOfProduct;

    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "link_on_product_url")
    private String linkOnProductUrl;

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
    private int pickedItemsCount;

    @Column(name = "all_items_count")
    private int allItemsCount;

    @Column(name = "code_for_winner")
    private String codeForWinner;

    @Column(name = "date")
    private Timestamp date;


    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ShareState status;

    @Column(name = "creation_status")
    @Enumerated(EnumType.STRING)
    private CreateShareState creationStatus;

    @Column(name = "message_for_user")
    private String messageForUser;

    @OneToMany(mappedBy = "share")
    private List<Item> items;

    @Column(name = "place_country")
    private String placeCountry;

    @Column(name = "place_region")
    private String placeRegion;

    @Column (name = "place_city")
    private String placeCity;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(int countOfProduct) {
        this.countOfProduct = countOfProduct;
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

    public String getLinkOnProductUrl() {
        return linkOnProductUrl;
    }

    public void setLinkOnProductUrl(String linkOnProductUrl) {
        this.linkOnProductUrl = linkOnProductUrl;
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

    public String getCodeForWinner() {
        return codeForWinner;
    }

    public void setCodeForWinner(String codeForWinner) {
        this.codeForWinner = codeForWinner;
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
                countOfProduct == that.countOfProduct &&
                Double.compare(that.productPrice, productPrice) == 0 &&
                pickedItemsCount == that.pickedItemsCount &&
                allItemsCount == that.allItemsCount &&
                Objects.equals(description, that.description) &&
                Objects.equals(productImageId, that.productImageId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(linkOnProductUrl, that.linkOnProductUrl) &&
                Objects.equals(announcementDuration, that.announcementDuration) &&
                Objects.equals(shareDuration, that.shareDuration) &&
                Objects.equals(afterShareDuration, that.afterShareDuration) &&
                Objects.equals(color, that.color) &&
                Objects.equals(codeForWinner, that.codeForWinner) &&
                Objects.equals(date, that.date) &&
                Objects.equals(status, that.status);
//                &&
//                Objects.equals(companyId, that.companyId);
    }

    @Override public int hashCode() {
        return Objects.hash(id, description, countOfProduct, productImageId, productName, linkOnProductUrl,
                productPrice, announcementDuration, shareDuration, afterShareDuration, color, pickedItemsCount,
                allItemsCount, codeForWinner, date, status);
    }
}

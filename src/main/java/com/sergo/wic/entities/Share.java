package com.sergo.wic.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shares")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "count_of_product")
    private int countOfProduct;

    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_name")
    private String productName;

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

    @Column(name = "status_id")
    @Enumerated(EnumType.ORDINAL)
    private ShareState status;

    @Column(name = "company_id")
    private Integer companyId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "share")
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "place_address_id", referencedColumnName = "id")
    private Address placeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Address getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(Address placeAddress) {
        this.placeAddress = placeAddress;
    }

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
                Objects.equals(status, that.status) &&
                Objects.equals(companyId, that.companyId);
    }

    @Override public int hashCode() {
        return Objects.hash(id, description, countOfProduct, productImageId, productName, linkOnProductUrl,
                productPrice, announcementDuration, shareDuration, afterShareDuration, color, pickedItemsCount,
                allItemsCount, codeForWinner, date, status, companyId);
    }
}

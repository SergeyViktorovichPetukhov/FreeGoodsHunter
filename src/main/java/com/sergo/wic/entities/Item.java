package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.ItemState;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items",
       indexes = {
        @Index(name = "SHARE_INDEX",
               columnList = "share_id"),
        @Index(name = "ITEM_INDEX",
               columnList = "item_id")
})

public class Item {

    public Item(){}

    public Item(double lat, double lon){
        this.latitude = lat;
        this.longitude = lon;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ItemState state;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "item_id")
    private String itemId;

    @ManyToOne
    @JoinColumn(name = "share_id", referencedColumnName = "id", nullable = false)
    private Share share;

//    @OneToOne(mappedBy = "item", orphanRemoval = true)
    @ManyToOne
    @JoinColumn(name = "user_item_id", referencedColumnName = "id" )
    private UserItem userItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public ItemState getState() {
        return state;
    }

    public void setState(ItemState state) {
        this.state = state;
    }

    //
//    public Long getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(Long itemId) {
//        this.itemId = itemId;
//    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    //    public String getSharesId() {
//        return sharesId;
//    }
//
//    public void setSharesId(String sharesId) {
//        this.sharesId = sharesId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return id == that.id &&
                Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}

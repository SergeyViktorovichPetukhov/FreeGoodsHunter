package com.sergo.wic.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items",
       indexes = {
        @Index(name = "SHARE_INDEX",
               columnList = "share_id"),
//        @Index(name = "USER_INDEX",
//               columnList = "user_item_id")
})

public class Item {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

//    @Column(name = "item_id")
//    private Long itemId;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @ManyToOne
    @JoinColumn(name = "share_id", referencedColumnName = "id", nullable = false)
    private Share share;

    @OneToOne(mappedBy = "item", orphanRemoval = true)
    private UserItem userItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

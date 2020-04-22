package com.sergo.wic.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items",
       indexes = {
        @Index(name = "SHARE_INDEX",
               columnList = "share_id"),
        @Index(name = "USER_INDEX",
               columnList = "user_id")
})
public class Item {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;

    @ManyToOne
    @JoinColumn(name = "share_id", referencedColumnName = "id", nullable = false)
    private Share share;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                Double.compare(that.lon, lon) == 0 &&
                Double.compare(that.lat, lat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lon, lat);
    }
}

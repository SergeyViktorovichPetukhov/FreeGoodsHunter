package com.sergo.wic.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;

    @ManyToOne
    @JoinColumn(name = "share_id", referencedColumnName = "id", nullable = false)
    private Share share;


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
        return Objects.hash(id, lon, lat);
    }
}

//package com.sergo.wic.entities;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Objects;
//
////@Table(name = "address", schema = "public", catalog = "wic_db")
//@Entity
//@Table(name = "address")
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//
//    @Column(name = "country")
//    private String country;
//
//    @Column(name = "region")
//    private String region;
//
//    @Column(name = "city")
//    private String city;
//
//    @Column(name = "address_line")
//    private String addressLine;
//
//    @OneToMany(mappedBy = "placeAddress")
//    private List<Share> shares;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public void setRegion(String region) {
//        this.region = region;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getAddressLine() {
//        return addressLine;
//    }
//
//    public void setAddressLine(String addressLine) {
//        this.addressLine = addressLine;
//    }
//
//    public List<Share> getShares() {
//        return shares;
//    }
//
//    public void setShares(List<Share> shares) {
//        this.shares = shares;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Address that = (Address) o;
//        return id == that.id &&
//                Objects.equals(country, that.country) &&
//                Objects.equals(region, that.region) &&
//                Objects.equals(city, that.city) &&
//                Objects.equals(addressLine, that.addressLine);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, country, region, city, addressLine);
//    }
//}

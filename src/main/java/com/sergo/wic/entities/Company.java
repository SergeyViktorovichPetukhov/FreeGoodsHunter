package com.sergo.wic.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    public Company(){}

    public Company(String login, User user) {
        this.login = login;
        this.user = user;
    }

    public Company(String login, String contact, User user) {
        this.login = login;
        this.contact = contact;
        this.user = user;
    }

    public Company(String login, String address, String contact, String code) {
        this.login = login;
        this.address = address;
        this.contact = contact;
        this.code = code;
    }

    public Company(String login, String address, String contact, String code, String internetShop) {
        this.login = login;
        this.address = address;
        this.contact = contact;
        this.code = code;
        this.internetShop = internetShop;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "internet_shop")
    private String internetShop;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "info")
    private String info;
    @Column(name = "is_verificated")
    private boolean isVerificated;
    @Column(name = "logo_url")
    private String logoUrl;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Share> shares;

    public boolean isVerificated() {
        return isVerificated;
    }

    public void setVerificated(boolean verificated) {
        isVerificated = verificated;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInternetShop() {
        return internetShop;
    }

    public void setInternetShop(String internetShop) {
        this.internetShop = internetShop;
    }

    public String getCode() {
        return code;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    public byte[] getLabel() {
//        return label;
//    }
//
//    public void setLabel(byte[] label) {
//        this.label = label;
//    }
}

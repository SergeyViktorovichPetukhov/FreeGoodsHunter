package com.sergo.wic.entities;

import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "companies")
public class Company {
    public Company(){}

    public Company(String login, String address, String phone, String code) {
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.code = code;
    }

    public Company(String login, String address, String phone, String code, String internetShop) {
        this.login = login;
        this.address = address;
        this.phone = phone;
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
    @Column(name = "phone")
    private String phone;
    @Column(name = "internetShop")
    private String internetShop;
    @Column(name = "code")
    private String code;

 //   private byte[] label;

    @Column(name = "label_path")
    private String label_path;

    @OneToOne(mappedBy = "company", fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Share> shares;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getLabel_path() {
        return label_path;
    }

    public void setLabel_path(String label_path) {
        this.label_path = label_path;
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

//    public byte[] getLabel() {
//        return label;
//    }
//
//    public void setLabel(byte[] label) {
//        this.label = label;
//    }
}

package com.sergo.wic.entities;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    public Company(){}

    public Company(String login, String address, String phone, Integer code) {
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.code = code;
    }

    public Company(String login, String address, String phone, Integer code, String internetShop) {
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.code = code;
        this.internetShop = internetShop;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    private Integer code;
    @Column(name = "label")
    private byte[] label;

    @OneToOne(mappedBy = "company")
    private User user;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public byte[] getLabel() {
        return label;
    }

    public void setLabel(byte[] label) {
        this.label = label;
    }
}

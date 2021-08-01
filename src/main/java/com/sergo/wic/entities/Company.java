package com.sergo.wic.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "companies")
public class Company {

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
    private Boolean isVerificated;
    @Column(name = "logo_url")
    private String logoUrl;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Share> shares;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @Override
    public String toString(){return "";}

}

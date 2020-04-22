package com.sergo.wic.entities;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "registrations")
public class Registration {

    public Registration(){}

    public Registration(String login, String phone, String code) {
        this.login = login;
        this.phone = phone;
        this.code = code;
    }

    public Registration(String login, String address, String phone, Long userId) {
        this.login = login;
        this.address = address;
        this.phone = phone;
        this.userId = userId;
    }

    public Registration(String login, String code, String phone, Long userId, boolean isNew) {
        this.login = login;
        this.code = code;
        this.phone = phone;
        this.userId = userId;
        this.isNew= isNew;
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
    @Column(name = "code")
    private String code;
    @Column(name = "is_new")
    private boolean isNew;
    @Column(name = "is_confirmed")
    private boolean isConfirmed;
    @Column(name = "reason_of_refuse")
    private String reasonOfRefuse;
    @Column(name = "user_id")
    private Long userId;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getReasonOfRefuse() {
        return reasonOfRefuse;
    }

    public void setReasonOfRefuse(String reasonOfRefuse) {
        this.reasonOfRefuse = reasonOfRefuse;
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return id == that.id &&
                this.address.equals(((Registration) o).getAddress())
               &this.login.equals(((Registration) o).getLogin())
               &this.phone.equals(((Registration)o).getPhone()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phone);
    }
}

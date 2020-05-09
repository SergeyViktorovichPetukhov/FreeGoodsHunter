package com.sergo.wic.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Table(name = "users"
        ,
       indexes = {
       @Index(name = "LOGIN_INDEX",
              columnList = "login")
          }
          )
public class User {

    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;

    @Column(name = "all_items_count")
    private Integer allItemsCount;
    @Column(name = "picked_items_count")
    private Integer pickedItemsCount;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "name")
    private String name;
    @Column(name = "hasCompany")
    private boolean hasCompany;     // if user is a company manager

    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user", fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private UserItem userItem;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Company getCompany() { return company; }

    public void setCompany(Company userCompany) { this.company = userCompany; }

    public boolean isHasCompany() { return hasCompany; }

    public void setHasCompany(boolean hasCompany) { this.hasCompany = hasCompany; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem item) {
        this.userItem = item;
    }

    public void setAllItemsCount(Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public Integer getPickedItemsCount() {
        return pickedItemsCount;
    }

    public void setPickedItemsCount(Integer pickedItemsCount) {
        this.pickedItemsCount = pickedItemsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public long getSharesCount(final Share currentShare){
        for (Share share : this.company.getShares()){
        System.out.println(share.getId());
        }
        return this.company
           .getShares()
              .stream()
                 .filter(share -> (LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                                          .isEqual(share.getDate().toLocalDateTime().truncatedTo(ChronoUnit.DAYS))))
                     .count() + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                Objects.equals(login, that.login);
    }

    @Override public int hashCode() {
        return Objects.hash(id, login, name);
    }
}

package com.sergo.wic.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
public class User {

    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "win_count")
    private Integer winCount;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "name")
    private String name;
    @Column(name = "isConfirmed")
    private boolean isConfirmed;     // if user is a company manager

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Company getCompany() { return company; }

    public void setCompany(Company userCompany) { this.company = userCompany; }

    public boolean isConfirmed() { return isConfirmed; }

    public void setConfirmed(boolean confirmed) { isConfirmed = confirmed; }

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

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public long getSharesCount(final Share currentShare){
        return this.company
           .getShares()
              .stream()
                 .filter(share -> (LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                                          .isEqual(share.getDate().toLocalDateTime().truncatedTo(ChronoUnit.DAYS))))
                     .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&
                winCount == that.winCount &&
                Objects.equals(login, that.login) &&
                Objects.equals(name, that.name);
    }

    @Override public int hashCode() {
        return Objects.hash(id, winCount, login, name);
    }
}

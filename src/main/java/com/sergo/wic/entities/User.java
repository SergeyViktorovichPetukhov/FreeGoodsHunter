package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.ShareCellType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users"
        ,
       indexes = {
       @Index(name = "LOGIN_INDEX",
              columnList = "login")
          }
       )
public class User {

    public User(String login){
        this.login = login;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;
    @Column(name = "login")
    private String login;

    @Column(name = "all_items_count")
    private Integer allItemsCount;
    @Column(name = "picked_items_count")
    private Integer pickedItemsCount;
    @Column(name = "contact")
    private String contact;
    @Column(name = "name")
    private String name;
    @Column(name = "is_company_reg_in_process")
    private boolean isCompanyRegInProcess;
    @Column(name = "hasCompany")
    private boolean hasCompany;     // if user is a company manager
    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user", fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Winning> winnings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserItem> userItems;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @OneToMany
    private List<Share> chosenShares;

    @OneToMany
    private List<Share> startedShares;

    @Transactional
    public void chooseShare(Share share){
        chosenShares.add(share);
    }
    @Transactional
    public void removeShare(Share share){
        chosenShares.remove(share);
    }
    @Transactional
    public void startShare(Share share){
        startedShares.add(share);
    }
    @Transactional
    public void finishShare(Share share){
        startedShares.remove(share);
    }

    public long getSharesCount(Share currentShare){
        return this.company.getShares().stream()
                 .filter(share ->
                         (LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)
                         .isEqual(share.getDate().toLocalDateTime().truncatedTo(ChronoUnit.DAYS)))
                 )
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

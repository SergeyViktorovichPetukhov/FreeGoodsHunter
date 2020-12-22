package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.TypeContact;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    public UserProfile(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "win_count")
    private Integer winCount;
    @Column(name = "range")
    private Double range;
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<Contact> contacts;
    @OneToOne
    private User user;

    public Contact getContact(TypeContact typeContact){
        return contacts.stream()
                .filter(contact -> contact.getTypeContact().equals(typeContact))
                .findFirst()
                .orElse(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return getId() == that.getId() &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getPhotoUrl(), that.getPhotoUrl()) &&
                Objects.equals(getWinCount(), that.getWinCount()) &&
                Objects.equals(getRange(), that.getRange()) &&
                Objects.equals(getContacts(), that.getContacts()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPhotoUrl(), getWinCount(), getRange(), getContacts(), getUser());
    }
}

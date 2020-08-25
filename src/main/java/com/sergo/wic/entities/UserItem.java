package com.sergo.wic.entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user_items")
public class UserItem {
    public UserItem(){}

    public UserItem(User user, List<Item> items){
        this.items = items;
        this.user = user;
    }
    public UserItem(User user){
        this.user = user;
    }
    public UserItem(List<Item> items){
        this.items = items;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "userItem", fetch = FetchType.LAZY)
    private Share share;

    @OneToMany(mappedBy = "userItem", cascade = {CascadeType.MERGE})
 //   @JoinColumn(name = "item_id",referencedColumnName = "id")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public List<Item> getItem() {
        return items;
    }

    public void setItem(List<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

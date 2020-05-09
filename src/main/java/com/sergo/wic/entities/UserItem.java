package com.sergo.wic.entities;

import javax.persistence.*;


@Entity
@Table(name = "user_items")
public class UserItem {
    public UserItem(){}

    public UserItem(User user, Item item){
        this.item = item;
        this.user = user;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;



    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    private Item item;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

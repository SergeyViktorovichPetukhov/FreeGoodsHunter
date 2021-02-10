package com.sergo.wic.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_items")
public class UserItem {

    public UserItem(User user, List<Item> items){
        this.items = items;
        this.user = user;
    }

    public UserItem(User user,Item item){
        items = new ArrayList<>();
        this.user = user;
        this.items.add(item);
    }

    public UserItem(UserItem userItem){
        this.items = userItem.getItems();
        this.user = userItem.getUser();
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Share share;

    @OneToMany(mappedBy = "userItem", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
 //   @JoinColumn(name = "item_id",referencedColumnName = "id")
    private List<Item> items;

}

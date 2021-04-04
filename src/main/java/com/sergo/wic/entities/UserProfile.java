package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.TypeContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
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
    @Column(name = "region")
    private String region;
    @Column (name = "collection_area")
    private Integer collection_area;
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
}

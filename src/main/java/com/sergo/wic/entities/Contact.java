package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.TypeContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type_contact")
    @Enumerated(EnumType.STRING)
    private TypeContact typeContact;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id" )
    private Company company;

    public Contact(final TypeContact typeContact, final String contact) {
        this.typeContact = typeContact;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact1 = (Contact) o;
        return getId() == contact1.getId() &&
                getTypeContact() == contact1.getTypeContact() &&
                Objects.equals(getContact(), contact1.getContact()) &&
                Objects.equals(getUserProfile(), contact1.getUserProfile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTypeContact(), getContact(), getUserProfile());
    }
}

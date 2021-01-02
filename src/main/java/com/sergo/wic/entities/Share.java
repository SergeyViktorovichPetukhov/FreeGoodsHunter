package com.sergo.wic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sergo.wic.entities.enums.CreateShareState;
import com.sergo.wic.entities.enums.ShareState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "shares",
       indexes = {
       @Index(name = "SHARE_ID_INDEX",
              columnList = "share_id")
          }
)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Share {

    public Share(){
        this.creationStatus = CreateShareState.CREATED;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;

    @JsonIgnore
    @Column(name = "share_id")
    private String shareId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id" )
    private Company company;

    @Column(name = "login")
    private String login;

    @Column(name = "product_photo_url")
    private String productPhotoUrl;

    @Column(name = "product_website")
    private String productWebsite;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_web_site")
    private String productWebSite;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "announcement_duration")
    private Integer announcementDuration;

    @Column(name = "share_duration")
    private Integer shareDuration;

    @Column(name = "after_share_duration")
    private Integer afterShareDuration;

    @Column(name = "color")
    private String color;

    @JsonIgnore
    @Column(name = "picked_items_count")
    private Integer pickedItemsCount;

    @JsonIgnore
    @Column(name = "all_items_count")
    private Integer allItemsCount;
    @JsonIgnore
    @Column(name = "code")
    private String code;
    @JsonIgnore
    @Column(name = "date")
    private Timestamp date;

    @JsonIgnore
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ShareState status;

    @Column(name = "creation_status")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private CreateShareState creationStatus;
    @JsonIgnore
    @Column(name = "message_for_user")
    private String messageForUser;
    @JsonIgnore
    @OneToMany(mappedBy = "share", cascade = {CascadeType.ALL})
    private List<Item> items;

//    @OneToMany
//    @JoinColumn(name="user_item_id", referencedColumnName = "id")
//    private UserItem userItem;

    @Column(name = "place_country")
    private String placeCountry;

    @Column(name = "place_region")
    private String placeRegion;

    @Column (name = "place_city")
    private String placeCity;

    public boolean addItem(Item item){

        return items.add(item);
    }


//    @ManyToOne
//    @JoinColumn(name = "place_address_id", referencedColumnName = "id")
//    private Address placeAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Share that = (Share) o;
        return id == that.id &&
                shareId.equals(that.getShareId());
    }

    @Override public int hashCode() {
        return Objects.hash( productPrice, announcementDuration, shareDuration,
                            afterShareDuration, pickedItemsCount, allItemsCount);

    }

}

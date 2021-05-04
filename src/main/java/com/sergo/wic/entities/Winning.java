package com.sergo.wic.entities;

import com.sergo.wic.entities.enums.PrizeType;
import com.sergo.wic.entities.enums.WinningState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "winnings")
public class Winning {
    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;
    @Column(name = "is_complained")
    private Boolean isComplained;
    @Column(name = "is_reviewed")
    private Boolean isReviewed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "share_id", referencedColumnName = "id")
    private Share share;
    @Column(name = "date")
    private Date date;
    @Column(name = "name")
    private String name;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private WinningState state;
    @Column(name = "amount_of_items")
    private Integer amountOfItems;
    @Column(name = "prize_url")
    private String prizeUrl;
    @Column(name = "prize_type")
    @Enumerated(EnumType.STRING)
    private PrizeType prizeType;

}

package com.sergo.wic.entities;

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
    @Column(name = "is_viewed")
    private boolean isViewed;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "date")
    private Date date;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private WinningState state;
}

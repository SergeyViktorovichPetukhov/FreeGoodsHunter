package com.sergo.wic.entities;

import javax.persistence.*;

@Entity
@Table(name = "share_items")
public class ShareItems {

    public ShareItems(){}

    public ShareItems( Share share){
        this.share = share;
    }

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "share_id", referencedColumnName = "id")
    private Share share;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="share_item", referencedColumnName = "id")
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}

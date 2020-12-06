package com.sergo.wic.entities;

import javax.persistence.*;

@Entity
@Table(name = "settlements",
       indexes = {
        @Index(name = "SETTLEMENT_NAME_INDEX",
                columnList = "s_name"),
        @Index(name = "SETTLEMENT_COUNTRY_INDEX",
                columnList = "country")
        }
)
public class Settlement {
    public Settlement(){}

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    private Long id;
    @Column(name = "s_name")
    private String sName;
    @Column(name = "country")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "language")
    private String language;
    @Column(name = "total_area")
    private Double totalArea;
    @Column(name = "useful_area")
    private Double usefulArea;
    @Column(name = "max_count_items")
    private Integer maxCountItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setName(String sName) {
        this.sName = sName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
    }

    public Double getUsefulArea() {
        return usefulArea;
    }

    public void setUsefulArea(Double usefulArea) {
        this.usefulArea = usefulArea;
    }

    public Integer getMaxCountItems() {
        return maxCountItems;
    }

    public void setMaxCountItems(Integer maxCountItems) {
        this.maxCountItems = maxCountItems;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

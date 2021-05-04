package com.sergo.wic.entities.enums;

public enum PrizeType {
    READY_TO_COLLECT("READY_TO_COLLECT"),
    COLLECTION_UNCONFIRMED("COLLECTION_UNCONFIRMED"),
    COLLECTION_CONFIRMED("COLLECTION_CONFIRMED"),
    COMPLETED("COMPLETED");

    private String type;

    PrizeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

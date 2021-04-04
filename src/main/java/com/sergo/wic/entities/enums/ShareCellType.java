package com.sergo.wic.entities.enums;

public enum ShareCellType {

    STARTED("STARTED", 1),
    CHOSEN("CHOSEN" , 2),
    ACTIVE("ACTIVE", 3),
    PREVIEW("PREVIEW", 4),
    FINISHED("FINISHED", 5);


    private String cell;
    private int value;

    public String getCell(){
        return this.cell;
    }

    public int getValue() {
        return value;
    }

    ShareCellType(String cell , int value){
        this.cell = cell;
        this.value = value;
    }
}

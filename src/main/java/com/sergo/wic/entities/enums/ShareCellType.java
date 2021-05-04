package com.sergo.wic.entities.enums;

public enum ShareCellType {

    STARTED("STARTED", 0),
    CHOSEN("CHOSEN" , 1),
    ACTIVE("ACTIVE", 2),
    PREVIEW("PREVIEW", 3),
    FINISHED("FINISHED", 4);


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

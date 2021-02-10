package com.sergo.wic.entities.enums;

public enum ShareCellType {

    STARTED("STARTED"), ACTIVE("ACTIVE"), PREVIEW("PREVIEW"), FINISHED("FINISHED"), CHOSEN("CHOSEN");

    private String cell;

    public String getCell(){
        return this.cell;
    }

    ShareCellType(String cell){
        this.cell = cell;
    }
}

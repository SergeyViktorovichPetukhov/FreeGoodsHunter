package com.sergo.wic.entities.enums;

public enum WinningState {
    PICK_UP_BEFORE("PICK_UP_BEFORE"), HAS_PICKED_UP("HAS_PICKED_UP"), FINISHED("FINISHED");

    String state;

    WinningState(String state) {
        this.state = state;
    }

    public String getState(){
        return this.state;
    }
}

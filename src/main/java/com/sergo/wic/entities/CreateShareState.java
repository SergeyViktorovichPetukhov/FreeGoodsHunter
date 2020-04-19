package com.sergo.wic.entities;

public enum CreateShareState {

    CREATED ("created"), CONFIRMED ("confirmed"), REFUSED("refused");
    private String state;

    public String getState()
    {
        return this.state;
    }

    CreateShareState(String state)
    {
        this.state = state;
    }
}

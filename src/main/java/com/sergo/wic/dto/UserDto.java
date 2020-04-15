package com.sergo.wic.dto;

public class UserDto {
    private String login;
    private Integer winCount;
    private String name;

    public UserDto() {
    }

    public UserDto(final String login, final Integer winCount, final String name) {
        this.login = login;
        this.winCount = winCount;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(final Integer winCount) {
        this.winCount = winCount;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

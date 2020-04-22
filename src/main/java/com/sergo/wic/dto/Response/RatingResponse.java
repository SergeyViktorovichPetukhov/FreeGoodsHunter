package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.UserDto;

import java.util.List;

public class RatingResponse extends Response {

    private List<UserDto> users;

    public RatingResponse() {
    }

    public RatingResponse(final List<UserDto> users, final Boolean isSuccess, final Integer errorCode) {
        this.users = users;
        setSuccess(isSuccess);
        setErrorCode(errorCode);
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(final List<UserDto> users) {
        this.users = users;
    }
}

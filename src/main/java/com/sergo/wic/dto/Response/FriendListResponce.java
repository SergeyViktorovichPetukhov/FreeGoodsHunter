package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.UserDto;

import java.util.List;

public class FriendListResponce extends Response {

    private List<UserDto> friends;

    public FriendListResponce() {}

    public FriendListResponce(final List<UserDto> friends, final Boolean isSuccess, final Integer errorCode) {
        this.friends = friends;
        setSuccess(isSuccess);
        setErrorCode(errorCode);
    }

    public List<UserDto> getFriends() {
        return friends;
    }

    public void setFriends(final List<UserDto> friends) {
        this.friends = friends;
    }
}

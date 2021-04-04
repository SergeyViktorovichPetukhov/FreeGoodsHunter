package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.UserDto;

import java.util.List;

public class FriendListResponse extends Response {

    private List<UserDto> friends;

    public FriendListResponse() {}

    public FriendListResponse(final List<UserDto> friends, final Boolean isSuccess, final Integer errorCode) {
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

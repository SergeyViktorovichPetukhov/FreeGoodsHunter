package com.sergo.wic.dto;

import java.util.List;

public class NoticeResponse extends Response {
    private List<NoticeDto> notices;

    public NoticeResponse() {
    }

    public NoticeResponse(List<NoticeDto> notices) {
        this.notices = notices;
    }

    public NoticeResponse(boolean isSuccess, int errorCode, List<NoticeDto> notices) {
        super(isSuccess, errorCode);
        this.notices = notices;
    }

    public List<NoticeDto> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticeDto> notices) {
        this.notices = notices;
    }
}

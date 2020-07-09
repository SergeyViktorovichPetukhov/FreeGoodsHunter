package com.sergo.wic.service.sms;

public interface SmsService {
    void sendVerifyCode(String code, String phone);
}

package com.sergo.wic.service.email;

public interface EmailService {
    void sendSimpleMessage(final String to, final String subject, final String text);
}

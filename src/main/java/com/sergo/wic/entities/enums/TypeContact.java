package com.sergo.wic.entities.enums;

public enum TypeContact {
    VIBER(0, "VIBER"), VK(1, "VK"), FACEBOOK(2, "FACEBOOK"), WHATSAPP(7, "WHATSAPP"),
    TELEGRAM(3, "TELEGRAM"), SKYPE(4, "SKYPE"), PHONE_NUMBER(5, "PHONE_NUMBER") , E_MAIL(6, "E_MAIL");

    private int id;
    private String name;

    TypeContact(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

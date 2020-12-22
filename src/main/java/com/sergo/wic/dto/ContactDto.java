package com.sergo.wic.dto;

import com.sergo.wic.entities.enums.TypeContact;

public class ContactDto {
    private TypeContact typeContact;
    private String contact;

    public ContactDto(){}

    public ContactDto(final TypeContact typeContact, final String contact) {
        this.typeContact = typeContact;
        this.contact = contact;
    }

    public TypeContact getTypeContact() {
        return typeContact;
    }

    public void setTypeContact(final TypeContact typeContact) {
        this.typeContact = typeContact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }
}

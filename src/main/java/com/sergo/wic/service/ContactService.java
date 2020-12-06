package com.sergo.wic.service;

import com.sergo.wic.entities.Contact;

import java.util.Optional;

public interface ContactService {
    Optional<Contact> findByContact(String contact);
    Contact save(Contact contact);
    void delete(Contact contact);

}

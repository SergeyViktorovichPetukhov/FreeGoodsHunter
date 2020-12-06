package com.sergo.wic.service.impl;

import com.sergo.wic.entities.Contact;
import com.sergo.wic.repository.ContactRepository;
import com.sergo.wic.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;

    @Override
    public Optional<Contact> findByContact(String contact) {
        return repository.findByContact(contact);
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        repository.delete(contact);
    }
}

package com.example.contactsproject.service;

import com.example.contactsproject.entity.Contacts;

import java.util.List;

public interface ContactsService {

    List<Contacts> getAllContacts();

    Contacts getContactById(Long id);

    Contacts saveContact(Contacts contact);

    Contacts updateContact(Contacts contact);

    void deleteContactById(Long id);

}
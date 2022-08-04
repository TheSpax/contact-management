package com.example.contactsproject.service;

import com.example.contactsproject.entity.ContactType;

import java.util.List;

public interface ContactTypeService {

    List<ContactType> getAllContactTypes();

    ContactType getContactTypeById(Long id);

    ContactType saveContactType(ContactType contactType);

    ContactType updateContactType(ContactType contactType);

    void deleteContactTypeById(Long id);
}

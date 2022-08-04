package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.Contacts;
import com.example.contactsproject.repository.ContactsRepository;
import com.example.contactsproject.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepository;

    @Override
    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    @Override
    public Contacts getContactById(Long id) {
        return contactsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Contact doesn't exist"));
    }

    @Override
    public Contacts saveContact(Contacts contact) {
        return contactsRepository.save(contact);
    }

    @Override
    public Contacts updateContact(Contacts contact) {
        return contactsRepository.save(contact);
    }

    @Override
    public void deleteContactById(Long id) {
        contactsRepository.deleteById(id);
    }
}

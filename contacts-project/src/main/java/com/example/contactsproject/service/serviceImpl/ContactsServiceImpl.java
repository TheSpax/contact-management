package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.Contacts;
import com.example.contactsproject.repository.ContactsRepository;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactsServiceImpl implements GenericService<Contacts> {

    private final ContactsRepository contactsRepository;

    @Override
    public List<Contacts> getAll() {
        return contactsRepository.findAll();
    }

    @Override
    public Contacts getByUid(UUID uid) {
        return contactsRepository.findByUid(uid);
    }

    @Override
    public Contacts save(Contacts contact) {
        contact.setUid(UUID.randomUUID());
        return contactsRepository.save(contact);
    }

    @Override
    public Contacts update(Contacts contact) {
        return contactsRepository.save(contact);
    }

    @Transactional
    @Override
    public void deleteByUid(UUID uid) {
        contactsRepository.deleteByUid(uid);
    }
}

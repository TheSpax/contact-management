package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl {

    private final ContactTypeRepository contactTypeRepository;

    public List<ContactType> getAll() {
        return contactTypeRepository.findAll();
    }

    public ContactType getByUid(UUID uid) {
        return contactTypeRepository.findByUid(uid);
    }

    public ContactType save(ContactType contactType) {
        contactType.setUid(UUID.randomUUID());
        return contactTypeRepository.save(contactType);
    }

    public ContactType update(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        contactTypeRepository.deleteByUid(uid);
    }
}

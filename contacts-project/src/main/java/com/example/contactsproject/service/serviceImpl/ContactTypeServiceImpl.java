package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.repository.ContactTypeRepository;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements GenericService<ContactType> {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactType> getAll() {
        return contactTypeRepository.findAll();
    }

    @Override
    public ContactType getByUid(UUID uid) {
        return contactTypeRepository.findByUid(uid);
    }

    @Override
    public ContactType save(ContactType contactType) {
        contactType.setUid(UUID.randomUUID());
        return contactTypeRepository.save(contactType);
    }

    @Override
    public ContactType update(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Transactional
    @Override
    public void deleteByUid(UUID uid) {
        contactTypeRepository.deleteByUid(uid);
    }
}

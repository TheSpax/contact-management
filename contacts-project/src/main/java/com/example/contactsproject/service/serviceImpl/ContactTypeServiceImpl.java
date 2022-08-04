package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.repository.ContactTypeRepository;
import com.example.contactsproject.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactType> getAllContactTypes() {
        return contactTypeRepository.findAll();
    }

    @Override
    public ContactType getContactTypeById(Long id) {
        return contactTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Contact type doesn't exist"));
    }

    @Override
    public ContactType saveContactType(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Override
    public ContactType updateContactType(ContactType contactType) {
        return contactTypeRepository.save(contactType);
    }

    @Override
    public void deleteContactTypeById(Long id) {
        contactTypeRepository.deleteById(id);
    }
}

package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.entity.Contact;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.ContactTypeRepository;
import com.example.contactsproject.repository.ContactRepository;
import com.example.contactsproject.repository.UserRepository;
import com.example.contactsproject.service.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final UserRepository userRepository;
    private final ContactTypeRepository contactTypeRepository;

    public List<ContactResponseDTO> getAll() {
        return contactMapper.mapAllContactsToContactDTO(contactRepository.findAll());
    }

    public ContactResponseDTO getByUid(UUID uid) {
        return contactMapper.mapContactToContactDTO(contactRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("Contact not found")));
    }

    public void update(UUID uid, ContactRequestDTO contactRequestDTO) {
        Contact contact = contactMapper.mapContactFromDTOUpdate(uid, contactRequestDTO);
        contactRepository.save(contact);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        contactRepository.deleteByUid(uid);
    }

    public void saveByUserUid(UUID uid, ContactRequestDTO contactRequestDTO) {
        User user = userRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ContactType contactType = contactTypeRepository.findByUid(contactRequestDTO.getContactTypeUid()).orElseThrow(() -> new EntityNotFoundException("ContactType not found"));
        Contact contact = contactMapper.mapContactFromContactDTO(contactRequestDTO);
        contact.setUser(user);
        contact.setContactType(contactType);
        contactRepository.save(contact);
    }
}

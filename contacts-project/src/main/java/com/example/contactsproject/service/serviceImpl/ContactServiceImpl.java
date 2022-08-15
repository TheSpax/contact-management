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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final UserRepository userRepository;
    private final ContactTypeRepository contactTypeRepository;

//    public Page<ContactResponseDTO> getAll(Pageable pageable) {
//        return contactMapper.mapContactsToPageContactsDTO(contactRepository.findAll(pageable));
//    }

    public ContactResponseDTO getByUid(UUID contactUid, UUID userUid) {
        return contactMapper.mapContactToContactDTO(contactRepository.findByUidAndUser_Uid(contactUid, userUid).orElseThrow(() -> new EntityNotFoundException("Contact not found")));
    }

    public void update(UUID contactUid, ContactRequestDTO contactRequestDTO, User user) {
        Contact contact = contactMapper.mapContactFromDTOUpdate(contactUid, contactRequestDTO);
        if(contact.getUser().getUid() == user.getUid()) {
            contactRepository.save(contact);
        } else {
            throw new AccessDeniedException("Not allowed");
        }
    }

    @Transactional
    public void deleteByUid(UUID contactUid, UUID userUid) {
        Contact contact = contactRepository.findByUid(contactUid).orElseThrow(() -> new EntityNotFoundException("Contact not found"));
        if(contact.getUser().getUid() == userUid) {
            contactRepository.deleteByUid(contactUid);
        } else {
            throw new AccessDeniedException("Not Allowed");
        }

    }

    public void saveByUserUid(User user, ContactRequestDTO contactRequestDTO) {
        ContactType contactType = contactTypeRepository.findByUid(contactRequestDTO.getContactTypeUid()).orElseThrow(() -> new EntityNotFoundException("ContactType not found"));
        Contact contact = contactMapper.mapContactFromContactDTO(contactRequestDTO);
        contact.setUser(user);
        contact.setContactType(contactType);
        contactRepository.save(contact);
    }

    public Page<ContactResponseDTO> getAllContactsByUser(UUID userUid, Pageable pageable) {
        return contactMapper.mapContactsToPageContactsDTO(contactRepository.findAllByUser_Uid(userUid, pageable));
    }
}

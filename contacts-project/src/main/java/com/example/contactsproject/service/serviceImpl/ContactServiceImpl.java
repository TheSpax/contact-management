package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.Contact;
import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.ContactRepository;
import com.example.contactsproject.repository.ContactTypeRepository;
import com.example.contactsproject.service.mappers.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final ContactTypeRepository contactTypeRepository;

    private final UserServiceImpl userService;

    @Transactional(readOnly = true)
    public ContactResponseDTO getByUid(UUID contactUid, UUID userUid) {
        return contactMapper.mapContactToContactDTO(contactRepository.findByUidAndUser_Uid(contactUid, userUid).orElseThrow(() -> new EntityNotFoundException("Contact not found")));
    }

    @Transactional
    public Contact userContactUidCheck(UUID contactUid, UUID userUid) {
        Contact contact = contactRepository.findByUid(contactUid).orElseThrow(() -> new EntityNotFoundException("Contact not found"));
        if (contact.getUser().getUid().equals(userUid)) {
            return contact;
        } else {
            throw new AccessDeniedException("Not allowed");
        }
    }

    @Transactional
    public void update(UUID contactUid, ContactRequestDTO contactRequestDTO, UUID userUid) {
        Contact contact = this.userContactUidCheck(contactUid, userUid);
        ContactType contactType = getContactType(contactRequestDTO);
        contact = contactMapper.mapContactFromDTOUpdate(contact, contactRequestDTO, contactType);
        contactRepository.save(contact);
    }

    @Transactional
    public void deleteByUid(UUID contactUid, UUID userUid) {
        Contact contact = this.userContactUidCheck(contactUid, userUid);
        contactRepository.deleteByUid(contact.getUid());
    }

    @Transactional
    public void saveByUser(ContactRequestDTO contactRequestDTO) {
        User user = userService.getLoggedUser();
        ContactType contactType = getContactType(contactRequestDTO);
        Contact contact = contactMapper.mapContactFromContactDTO(contactRequestDTO);
        contact.setUser(user);
        contact.setContactType(contactType);
        contactRepository.save(contact);
    }

    @Transactional(readOnly = true)
    public Page<ContactResponseDTO> getAllContactsByUser(UUID userUid, Pageable pageable) {
        return contactMapper.mapContactsToPageContactsDTO(contactRepository.findAllByUser_Uid(userUid, pageable));
    }

    @Transactional(readOnly = true)
    public Page<ContactResponseDTO> getAllByFieldAdmin(String field, Pageable pageable) {
        return contactMapper.mapContactsToPageContactsDTO(contactRepository.findByField(field, pageable));
    }

    @Transactional(readOnly = true)
    public Page<ContactResponseDTO> getAllByField(UUID userUid, String field, Pageable pageable) {
        return contactMapper.mapContactsToPageContactsDTO(contactRepository.findByFieldPassedAndUser(field, userUid, pageable));
    }

    @Transactional(readOnly = true)
    public ContactType getContactType(ContactRequestDTO contactRequestDTO) {
        return contactTypeRepository.findByUid(contactRequestDTO.getContactTypeUid()).orElseThrow(() -> new EntityNotFoundException("Contact type not found"));
    }

}
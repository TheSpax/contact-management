package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.Contact;
import com.example.contactsproject.repository.ContactRepository;
import com.example.contactsproject.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private final ContactRepository contactRepository;
    private final ContactTypeRepository contactTypeRepository;

    public List<ContactResponseDTO> mapAllContactsToContactDTO(List<Contact> contacts) {
        List<ContactResponseDTO> dtoList = new ArrayList<>();
        for(Contact c : contacts) {
            ContactResponseDTO contactResponseDTO = mapContactToContactDTO(c);
            dtoList.add(contactResponseDTO);
        }
        return dtoList;
    }

    public ContactResponseDTO mapContactToContactDTO(Contact c) {
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO();
        contactResponseDTO.setUid(c.getUid());
        contactResponseDTO.setFirstName(c.getFirstName());
        contactResponseDTO.setLastName(c.getLastName());
        contactResponseDTO.setEmail(c.getEmail());
        contactResponseDTO.setPhoneNumber(c.getPhoneNumber());
        contactResponseDTO.setContactType(c.getContactType().getTypeName());
        contactResponseDTO.setTimeCreated(c.getTimeCreated());
        contactResponseDTO.setTimeUpdated(c.getTimeUpdated());

        return contactResponseDTO;
    }

    public Contact mapContactFromContactDTO(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        contact.setUid(UUID.randomUUID());
        contact.setFirstName(contactRequestDTO.getFirstName());
        contact.setLastName(contactRequestDTO.getLastName());
        contact.setEmail(contactRequestDTO.getEmail());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
        return contact;
    }

    public Contact mapContactFromDTOUpdate(UUID uid, ContactRequestDTO contactRequestDTO) {
        Contact contact = contactRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("Contact not found"));
        contact.setFirstName(contactRequestDTO.getFirstName());
        contact.setLastName(contactRequestDTO.getLastName());
        contact.setEmail(contactRequestDTO.getEmail());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
        contact.setContactType(contactTypeRepository.findByUid(contactRequestDTO.getContactTypeUid()).orElseThrow(() -> new EntityNotFoundException("ContactType not found")));
        return contact;
    }

    public Page<ContactResponseDTO> mapContactsToPageContactsDTO(Page<Contact> contacts) {
        return contacts.map(this::mapContactToContactDTO);
    }

}
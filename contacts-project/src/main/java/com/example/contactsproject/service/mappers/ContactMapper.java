package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.Contact;
import com.example.contactsproject.entity.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactMapper {

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
        mapBaseContact(contactRequestDTO, contact);
        return contact;
    }

    public Contact mapContactFromDTOUpdate(Contact contact, ContactRequestDTO contactRequestDTO, ContactType contactType) {
        mapBaseContact(contactRequestDTO, contact);
        contact.setContactType(contactType);
        return contact;
    }

    private static void mapBaseContact(ContactRequestDTO contactRequestDTO, Contact contact) {
        contact.setFirstName(contactRequestDTO.getFirstName());
        contact.setLastName(contactRequestDTO.getLastName());
        contact.setEmail(contactRequestDTO.getEmail());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
    }

    public Page<ContactResponseDTO> mapContactsToPageContactsDTO(Page<Contact> contacts) {
        return contacts.map(this::mapContactToContactDTO);
    }

}
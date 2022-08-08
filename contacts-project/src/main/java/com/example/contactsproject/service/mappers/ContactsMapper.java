package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.Contacts;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ContactsMapper {

    public List<ContactResponseDTO> mapAllContactsToContactDTO(List<Contacts> contacts) {
        List<ContactResponseDTO> dtoList = new ArrayList<>();
        for(Contacts c : contacts) {
            ContactResponseDTO contactResponseDTO = mapContactToContactDTO(c);
            dtoList.add(contactResponseDTO);
        }
        return dtoList;
    }

    public ContactResponseDTO mapContactToContactDTO(Contacts c) {
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

    public Contacts mapContactFromContactDTO(ContactRequestDTO contactRequestDTO) {
        Contacts contact = new Contacts();
        contact.setUid(UUID.randomUUID());
        contact.setFirstName(contactRequestDTO.getFirstName());
        contact.setLastName(contactRequestDTO.getLastName());
        contact.setEmail(contactRequestDTO.getEmail());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
        return contact;
    }

}
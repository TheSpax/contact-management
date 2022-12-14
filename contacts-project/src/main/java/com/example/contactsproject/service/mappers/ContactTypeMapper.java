package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.contactType.ContactTypeRequestDTO;
import com.example.contactsproject.controller.dto.contactType.ContactTypeResponseDTO;
import com.example.contactsproject.entity.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContactTypeMapper {

    public List<ContactTypeResponseDTO> mapContactTypesToContactTypesDTO(List<ContactType> contactTypes) {
        List<ContactTypeResponseDTO> contactTypeResponseDTOS = new ArrayList<>();
        for(ContactType ct : contactTypes) {
            ContactTypeResponseDTO contactTypeResponseDTO = mapContactTypeToContactTypeDTO(ct);
            contactTypeResponseDTOS.add(contactTypeResponseDTO);
        }
        return contactTypeResponseDTOS;
    }

    public ContactTypeResponseDTO mapContactTypeToContactTypeDTO(ContactType contactType) {
        ContactTypeResponseDTO contactTypeResponseDTO = new ContactTypeResponseDTO();
        contactTypeResponseDTO.setTypeName(contactType.getTypeName());
        contactTypeResponseDTO.setUid(contactType.getUid());
        return contactTypeResponseDTO;
    }

    public ContactType mapContactTypeFromContactTypeDTO(ContactTypeRequestDTO contactTypeRequestDTO) {
        ContactType contactType = new ContactType();
        contactType.setUid(UUID.randomUUID());
        contactType.setTypeName(contactTypeRequestDTO.getTypeName());
        return contactType;
    }

    public ContactType mapContactTypeFromDTOUpdate(ContactType contactType, ContactTypeRequestDTO contactTypeRequestDTO) {
        contactType.setTypeName(contactTypeRequestDTO.getTypeName());
        return contactType;
    }

}

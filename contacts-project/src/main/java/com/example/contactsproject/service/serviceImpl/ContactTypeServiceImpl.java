package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.contactType.ContactTypeRequestDTO;
import com.example.contactsproject.controller.dto.contactType.ContactTypeResponseDTO;
import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.repository.ContactTypeRepository;
import com.example.contactsproject.service.mappers.ContactTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl {

    private final ContactTypeRepository contactTypeRepository;
    private final ContactTypeMapper contactTypeMapper;

    public List<ContactTypeResponseDTO> getAll() {
        return contactTypeMapper.mapContactTypesToContactTypesDTO(contactTypeRepository.findAll());
    }

    public ContactTypeResponseDTO getByUid(UUID uid) {
        return contactTypeMapper.mapContactTypeToContactTypeDTO(contactTypeRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("ContactType not found")));
    }

    public void save(ContactTypeRequestDTO contactTypeRequestDTO) {
        ContactType contactType = contactTypeMapper.mapContactTypeFromContactTypeDTO(contactTypeRequestDTO);
        contactTypeRepository.save(contactType);
    }

    public void update(UUID uid, ContactTypeRequestDTO contactTypeRequestDTO) {
        ContactType contactType = contactTypeMapper.mapContactTypeFromDTOUpdate(uid, contactTypeRequestDTO);
        contactTypeRepository.save(contactType);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        contactTypeRepository.deleteByUid(uid);
    }
}

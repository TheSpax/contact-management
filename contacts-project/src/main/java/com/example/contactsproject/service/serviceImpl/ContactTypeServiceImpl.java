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

    @Transactional(readOnly = true)
    public List<ContactTypeResponseDTO> getAll() {
        return contactTypeMapper.mapContactTypesToContactTypesDTO(contactTypeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ContactTypeResponseDTO getByUid(UUID contactTypeUid) {
        return contactTypeMapper.mapContactTypeToContactTypeDTO(getContactType(contactTypeUid));
    }

    @Transactional
    public void save(ContactTypeRequestDTO contactTypeRequestDTO) {
        ContactType contactType = contactTypeMapper.mapContactTypeFromContactTypeDTO(contactTypeRequestDTO);
        contactTypeRepository.save(contactType);
    }

    @Transactional
    public void update(UUID contactTypeUid, ContactTypeRequestDTO contactTypeRequestDTO) {
        ContactType contactType = this.getContactType(contactTypeUid);
        contactType = contactTypeMapper.mapContactTypeFromDTOUpdate(contactType, contactTypeRequestDTO);
        contactTypeRepository.save(contactType);
    }

    @Transactional(readOnly = true)
    public ContactType getContactType(UUID contactTypeUid) {
        return contactTypeRepository.findByUid(contactTypeUid).orElseThrow(() -> new EntityNotFoundException("ContactType not found"));
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        contactTypeRepository.deleteByUid(uid);
    }
}

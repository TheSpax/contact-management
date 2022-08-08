package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.entity.Contacts;
import com.example.contactsproject.entity.Users;
import com.example.contactsproject.repository.ContactTypeRepository;
import com.example.contactsproject.repository.ContactsRepository;
import com.example.contactsproject.repository.UsersRepository;
import com.example.contactsproject.service.mappers.ContactsMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactsServiceImpl {

    private final ContactsRepository contactsRepository;
    private final ContactsMapper contactsMapper;
    private final UsersRepository usersRepository;
    private final ContactTypeRepository contactTypeRepository;

    public List<ContactResponseDTO> getAll() {
        return contactsMapper.mapAllContactsToContactDTO(contactsRepository.findAll());
    }

    public ContactResponseDTO getByUid(UUID uid) {
        return contactsMapper.mapContactToContactDTO(contactsRepository.findByUid(uid));
    }

    public Contacts save(Contacts contact) {
        contact.setUid(UUID.randomUUID());
        return contactsRepository.save(contact);
    }

    public Contacts update(Contacts contact) {
        return contactsRepository.save(contact);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        contactsRepository.deleteByUid(uid);
    }

    public void saveByUserUid(UUID uid, ContactRequestDTO contactRequestDTO) {
        Users user = usersRepository.findByUid(uid);
        ContactType contactType = contactTypeRepository.findByUid(contactRequestDTO.getContactTypeUid());
        Contacts contact = contactsMapper.mapContactFromContactDTO(contactRequestDTO);
        contact.setUser(user);
        contact.setContactType(contactType);
        contactsRepository.save(contact);
    }
}

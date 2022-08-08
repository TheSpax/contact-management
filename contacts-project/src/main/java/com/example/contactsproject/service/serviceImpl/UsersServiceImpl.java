package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.Users;
import com.example.contactsproject.repository.ContactsRepository;
import com.example.contactsproject.repository.UsersRepository;
import com.example.contactsproject.service.mappers.ContactsMapper;
import com.example.contactsproject.service.mappers.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final ContactsMapper contactsMapper;
    private final ContactsRepository contactsRepository;

    public List<UserResponseDTO> getAll() {
        return usersMapper.mapAllUsersToUserDTO(usersRepository.findAll());
    }

    public Users getByUid(UUID uid) {
        return usersRepository.findByUid(uid);
    }

    public void save(UserRequestDTO userRequestDTO) {
        Users user = usersMapper.mapUserFromUserDTO(userRequestDTO);
        usersRepository.save(user);
    }

    public Users update(Users user) {
        return usersRepository.save(user);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        usersRepository.deleteByUid(uid);
    }

    public List<ContactResponseDTO> getAllContactsByUserUid(UUID uid) {
        return contactsMapper.mapAllContactsToContactDTO(contactsRepository.findAllByUser_Uid(uid));
    }

}
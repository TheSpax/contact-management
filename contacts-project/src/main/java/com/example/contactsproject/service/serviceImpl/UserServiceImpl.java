package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.ContactRepository;
import com.example.contactsproject.repository.UserRepository;
import com.example.contactsproject.service.mappers.ContactMapper;
import com.example.contactsproject.service.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return userMapper.mapUsersToPageUsersDTO(userRepository.findAll(pageable));

    }

    public UserResponseDTO getByUid(UUID uid) {
        return userMapper.mapUserToUserDTO(userRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    public void save(UserRequestDTO userRequestDTO) {
        User user = userMapper.mapUserFromUserDTO(userRequestDTO);
        userRepository.save(user);
    }

    public void update(UUID uid, UserRequestDTO userRequestDTO) {
        User user = userMapper.mapUserFromDTOUpdate(uid, userRequestDTO);
        userRepository.save(user);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        userRepository.deleteByUid(uid);
    }

    public Page<ContactResponseDTO> getAllContactsByUserUid(UUID uid, Pageable pageable) {
        return contactMapper.mapContactsToPageContactsDTO(contactRepository.findAllByUser_Uid(uid, pageable));
    }

}
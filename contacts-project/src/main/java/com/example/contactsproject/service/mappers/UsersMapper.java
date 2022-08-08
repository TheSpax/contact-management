package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.Users;
import com.example.contactsproject.repository.ContactsRepository;
import com.example.contactsproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsersMapper {

    private final ContactsRepository contactsRepository;
    private final ContactsMapper contactsMapper;
    public final RoleRepository roleRepository;

    public List<UserResponseDTO> mapAllUsersToUserDTO(List<Users> users) {
        List<UserResponseDTO> dtoList = new ArrayList<>();
        for(Users u : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUid(u.getUid());
            userResponseDTO.setFirstName(u.getFirstName());
            userResponseDTO.setLastName(u.getLastName());
            userResponseDTO.setUsername(u.getUsername());
            userResponseDTO.setEmail(u.getEmail());
            userResponseDTO.setContactsList(contactsMapper.mapAllContactsToContactDTO(contactsRepository.findAllByUser_Uid(u.getUid())));
            dtoList.add(userResponseDTO);
        }
        return dtoList;
    }

    public Users mapUserFromUserDTO(UserRequestDTO userRequestDTO) {
        Users user = new Users();
        user.setUid(UUID.randomUUID());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(roleRepository.findByUid(userRequestDTO.getRoleUid()));
        return user;
    }

}
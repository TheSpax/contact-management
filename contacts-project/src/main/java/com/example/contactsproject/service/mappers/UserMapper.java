package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.Role;
import com.example.contactsproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User mapUserFromUserDTO(UserRequestDTO userRequestDTO, Role role) {
        User user = new User();
        user.setUid(UUID.randomUUID());
        this.mapBaseUser(userRequestDTO, user, role);
        return user;
    }

    public UserResponseDTO mapUserToUserDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUid(user.getUid());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setStatus(user.getStatus());
        return userResponseDTO;
    }

    public User mapUserFromDTOUpdate(User user, UserRequestDTO userRequestDTO, Role role) {
        this.mapBaseUser(userRequestDTO, user, role);
        return user;
    }

    private void mapBaseUser(UserRequestDTO userRequestDTO, User user, Role role) {
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setStatus("Not verified");
        user.setRole(role);
    }

    public Page<UserResponseDTO> mapUsersToPageUsersDTO(Page<User> users) {
        return users.map(this::mapUserToUserDTO);
    }

}
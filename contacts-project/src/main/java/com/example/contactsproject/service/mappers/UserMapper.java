package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.ContactRepository;
import com.example.contactsproject.repository.RoleRepository;
import com.example.contactsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    public final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> mapAllUsersToUserDTO(List<User> users) {
        List<UserResponseDTO> dtoList = new ArrayList<>();
        for(User u : users) {
            UserResponseDTO userResponseDTO = mapUserToUserDTO(u);
            dtoList.add(userResponseDTO);
        }
        return dtoList;
    }

    public User mapUserFromUserDTO(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUid(UUID.randomUUID());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(roleRepository.findByUid(userRequestDTO.getRoleUid()).orElseThrow(() -> new EntityNotFoundException("Role not found")));
        return user;
    }

    public UserResponseDTO mapUserToUserDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUid(user.getUid());
        return userResponseDTO;
    }

    public User mapUserFromDTOUpdate(UUID uid, UserRequestDTO userRequestDTO) {
        User user = userRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(roleRepository.findByUid(userRequestDTO.getRoleUid()).orElseThrow(() -> new EntityNotFoundException("Role not found")));
        return user;
    }

    public Page<UserResponseDTO> mapUsersToPageUsersDTO(Page<User> users) {
        return users.map(this::mapUserToUserDTO);
    }

}
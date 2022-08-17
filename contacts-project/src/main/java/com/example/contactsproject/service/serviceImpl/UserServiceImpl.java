package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.Role;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.RoleRepository;
import com.example.contactsproject.repository.UserRepository;
import com.example.contactsproject.service.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return userMapper.mapUsersToPageUsersDTO(userRepository.findAll(pageable));
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getByUid(UUID uid) {
        return userMapper.mapUserToUserDTO(userRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    @Transactional
    public void save(UserRequestDTO userRequestDTO) {
        User user = userMapper.mapUserFromUserDTO(userRequestDTO, this.getRole(userRequestDTO));
        userRepository.save(user);
    }

    @Transactional
    public void update(UUID userUid, UserRequestDTO userRequestDTO) {
        User user = userRepository.findByUid(userUid).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user = userMapper.mapUserFromDTOUpdate(user, userRequestDTO, this.getRole(userRequestDTO));
        userRepository.save(user);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        userRepository.deleteByUid(uid);
    }

    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    private Role getRole(UserRequestDTO userRequestDTO) {
        return roleRepository.findByUid(userRequestDTO.getRoleUid()).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

}
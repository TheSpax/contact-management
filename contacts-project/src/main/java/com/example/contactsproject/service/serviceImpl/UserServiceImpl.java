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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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

//    public Page<ContactResponseDTO> getAllContactsByUserUid(UUID uid, Pageable pageable) {
//        User loggedUser = getLoggedUser();
//
//        if(loggedUser.getUid().toString().equals(uid.toString())) {
//            return contactMapper.mapContactsToPageContactsDTO(contactRepository.findAllByUser_Uid(uid, pageable));
//        }
//        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//    }

//    public Page<ContactResponseDTO> getAllByUserUidAndField(UUID uid, String field, Pageable pageable) {
//        User loggedUser = getLoggedUser();
//
//        if(loggedUser.getUid().toString().equals(uid.toString())) {
//            return contactMapper.mapContactsToPageContactsDTO(contactRepository.findByFieldPassed(field, uid, pageable));
//        }
//        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//    }

    public User getLoggedUser() {
        String username = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return userRepository.findByUsername(username);
    }

}
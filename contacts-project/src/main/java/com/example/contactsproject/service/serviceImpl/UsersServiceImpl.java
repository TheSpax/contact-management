package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.dto.UserDTO;
import com.example.contactsproject.entity.Users;
import com.example.contactsproject.repository.UsersRepository;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl {

    private final UsersRepository usersRepository;

    private UserDTO convertToUserDTO(Users user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
    }

    public List<UserDTO> getAll() {
        return usersRepository.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    public UserDTO getByUid(UUID uid) {
        return convertToUserDTO(usersRepository.findByUid(uid));
    }

    public Users save(Users user) {
        user.setUid(UUID.randomUUID());
        return usersRepository.save(user);
    }

    public Users update(Users user) {
        return usersRepository.save(user);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        usersRepository.deleteByUid(uid);
    }
}
package com.example.contactsproject;

import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.repository.UserRepository;
import com.example.contactsproject.service.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @InjectMocks
    @Autowired
    UserService userService;

    @Test
    void testGetAllUsers() {
        Pageable pageable = Pageable.ofSize(3);
        User user = new User();
        user.setFirstName("Jovan");
        user.setLastName("Spasic");
        user.setEmail("jovan@gmail.com");
        user.setUsername("jovan123");
        user.setUid(UUID.randomUUID());

        List<User> users = new ArrayList<>();
        users.add(user);
        Page<User> page = new PageImpl<>(users);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUid(user.getUid());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUsername(user.getUsername());

        Mockito.when(userRepository.findAll(pageable)).thenReturn(page);

        Assertions.assertEquals(userResponseDTO, userService.getAll(pageable).getContent().get(0));
    }

}
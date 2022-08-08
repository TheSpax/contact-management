package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.Users;
import com.example.contactsproject.service.serviceImpl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Users> getUserByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(usersService.getByUid(uid));
    }

    @GetMapping("/{uid}/contacts")
    public List<ContactResponseDTO> getContactsByUserUid(@PathVariable UUID uid) {
        return usersService.getAllContactsByUserUid(uid);
    }

    @PostMapping()
    public ResponseEntity saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        usersService.save(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.update(user));
    }

    @DeleteMapping("/{uid}")
    public void deleteUserByUid(@PathVariable UUID uid) {
        usersService.deleteByUid(uid);
    }

}
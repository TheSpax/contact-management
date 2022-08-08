package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<UserResponseDTO> getUserByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(userService.getByUid(uid));
    }

    @GetMapping("/{uid}/contacts")
    public List<ContactResponseDTO> getContactsByUserUid(@PathVariable UUID uid) {
        return userService.getAllContactsByUserUid(uid);
    }

    @PostMapping()
    public ResponseEntity saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity updateUser(@PathVariable UUID uid, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.update(uid, userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}")
    public void deleteUserByUid(@PathVariable UUID uid) {
        userService.deleteByUid(uid);
    }

}
package com.example.contactsproject.controller;

import com.example.contactsproject.dto.UserDTO;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<UserDTO> getUserByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(usersService.getByUid(uid));
    }

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.save(user));
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
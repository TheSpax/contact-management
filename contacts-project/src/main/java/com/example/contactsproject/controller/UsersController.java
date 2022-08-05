package com.example.contactsproject.controller;

import com.example.contactsproject.entity.Users;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class UsersController {

    private final GenericService<Users> usersService;

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Users> getUserByUid(@PathVariable UUID uid) {
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
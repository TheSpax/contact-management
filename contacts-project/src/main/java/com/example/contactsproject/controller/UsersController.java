package com.example.contactsproject.controller;

import com.example.contactsproject.entity.Users;
import com.example.contactsproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.saveUser(user));
    }

    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        usersService.deleteUserById(id);
    }

}
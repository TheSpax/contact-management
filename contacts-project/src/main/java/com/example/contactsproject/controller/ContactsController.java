package com.example.contactsproject.controller;

import com.example.contactsproject.entity.Contacts;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts/v1")
@RequiredArgsConstructor
public class ContactsController {

    private final GenericService<Contacts> contactsService;

    @GetMapping("/all")
    public ResponseEntity<List<Contacts>> getAllUsers() {
        return ResponseEntity.ok(contactsService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Contacts> getUserById(@PathVariable UUID uid) {
        return ResponseEntity.ok(contactsService.getByUid(uid));
    }

    @PostMapping
    public ResponseEntity<Contacts> saveUser(@RequestBody Contacts contact) {
        return ResponseEntity.ok(contactsService.save(contact));
    }

    @PutMapping
    public ResponseEntity<Contacts> updateUser(@RequestBody Contacts contact) {
        return ResponseEntity.ok(contactsService.update(contact));
    }

    @DeleteMapping("/{uid}")
    public void deleteUserByUid(@PathVariable UUID uid) {
        contactsService.deleteByUid(uid);
    }


}
package com.example.contactsproject.controller;

import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contactTypes/v1")
@RequiredArgsConstructor
public class ContactTypeController {

    private final GenericService<ContactType> contactTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactType>> getAllUsers() {
        return ResponseEntity.ok(contactTypeService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ContactType> getUserByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(contactTypeService.getByUid(uid));
    }

    @PostMapping
    public ResponseEntity<ContactType> saveUser(@RequestBody ContactType contactType) {
        return ResponseEntity.ok(contactTypeService.save(contactType));
    }

    @PutMapping
    public ResponseEntity<ContactType> updateUser(@RequestBody ContactType contactType) {
        return ResponseEntity.ok(contactTypeService.update(contactType));
    }

    @DeleteMapping("/{uid}")
    public void deleteUserById(@PathVariable UUID uid) {
        contactTypeService.deleteByUid(uid);
    }

}
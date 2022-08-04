package com.example.contactsproject.controller;

import com.example.contactsproject.entity.ContactType;
import com.example.contactsproject.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactTypes/v1")
@RequiredArgsConstructor
public class ContactTypeController {

    private final ContactTypeService contactTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactType>> getAllUsers() {
        return ResponseEntity.ok(contactTypeService.getAllContactTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactType> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(contactTypeService.getContactTypeById(id));
    }

    @PostMapping
    public ResponseEntity<ContactType> saveUser(@RequestBody ContactType contactType) {
        return ResponseEntity.ok(contactTypeService.saveContactType(contactType));
    }

    @PutMapping
    public ResponseEntity<ContactType> updateUser(@RequestBody ContactType contactType) {
        return ResponseEntity.ok(contactTypeService.updateContactType(contactType));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        contactTypeService.deleteContactTypeById(id);
    }

}
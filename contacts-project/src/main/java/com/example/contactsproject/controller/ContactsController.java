package com.example.contactsproject.controller;

import com.example.contactsproject.entity.Contacts;
import com.example.contactsproject.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts/v1")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactsService contactsService;

    @GetMapping("/all")
    public ResponseEntity<List<Contacts>> getAllUsers() {
        return ResponseEntity.ok(contactsService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(contactsService.getContactById(id));
    }

    @PostMapping
    public ResponseEntity<Contacts> saveUser(@RequestBody Contacts contact) {
        return ResponseEntity.ok(contactsService.saveContact(contact));
    }

    @PutMapping
    public ResponseEntity<Contacts> updateUser(@RequestBody Contacts contact) {
        return ResponseEntity.ok(contactsService.updateContact(contact));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        contactsService.deleteContactById(id);
    }


}
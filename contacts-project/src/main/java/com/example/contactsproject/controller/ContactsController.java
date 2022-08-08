package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.Contacts;
import com.example.contactsproject.service.serviceImpl.ContactsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts/v1")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactsServiceImpl contactsService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {
        return ResponseEntity.ok(contactsService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ContactResponseDTO> getContactsById(@PathVariable UUID uid) {
        return ResponseEntity.ok(contactsService.getByUid(uid));
    }

    @PostMapping("/{uid}")
    public ResponseEntity saveContact(@PathVariable UUID uid, @RequestBody ContactRequestDTO contactRequestDTO) {
        contactsService.saveByUserUid(uid, contactRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Contacts> updateContact(@RequestBody Contacts contact) {
        return ResponseEntity.ok(contactsService.update(contact));
    }

    @DeleteMapping("/{uid}")
    public void deleteContactByUid(@PathVariable UUID uid) {
        contactsService.deleteByUid(uid);
    }


}
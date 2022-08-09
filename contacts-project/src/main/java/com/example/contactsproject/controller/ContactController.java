package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.service.serviceImpl.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactsService;

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> getAllContacts(Pageable pageable) {
        return ResponseEntity.ok(contactsService.getAll(pageable));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ContactResponseDTO> getContactsById(@PathVariable UUID uid) {
        return ResponseEntity.ok(contactsService.getByUid(uid));
    }

    @PostMapping("/{uid}")
    public ResponseEntity saveContact(@PathVariable UUID uid, @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        contactsService.saveByUserUid(uid, contactRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity updateContact(@PathVariable UUID uid, @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        contactsService.update(uid, contactRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}")
    public void deleteContactByUid(@PathVariable UUID uid) {
        contactsService.deleteByUid(uid);
    }


}
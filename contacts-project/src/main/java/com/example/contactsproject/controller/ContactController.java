package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.service.serviceImpl.ContactServiceImpl;
import com.example.contactsproject.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactsService;

    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> getAllContacts(Pageable pageable) {
        User user = userService.getLoggedUser();
        return ResponseEntity.ok(contactsService.getAllContactsByUser(user.getUid(), pageable));
    }

    @GetMapping("/{contactUid}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable UUID contactUid) {
        User user = userService.getLoggedUser();
        return ResponseEntity.ok(contactsService.getByUid(contactUid, user.getUid()));
    }

    @PostMapping
    public ResponseEntity saveContact(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        User user = userService.getLoggedUser();
        contactsService.saveByUserUid(user, contactRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{contactUid}")
    public ResponseEntity updateContact(@PathVariable UUID contactUid, @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        User user = userService.getLoggedUser();
        contactsService.update(contactUid, contactRequestDTO, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{contactUid}")
    public void deleteContactByUid(@PathVariable UUID contactUid) {
        User user = userService.getLoggedUser();
        contactsService.deleteByUid(contactUid, user.getUid());
    }


}
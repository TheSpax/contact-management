package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.exceptions.FileEmptyException;
import com.example.contactsproject.entity.User;
import com.example.contactsproject.service.serviceImpl.ContactServiceImpl;
import com.example.contactsproject.service.serviceImpl.ContactsImportService;
import com.example.contactsproject.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequestMapping("/user/contacts")
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactServiceImpl contactsService;

    private final ContactsImportService contactsImportService;

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

    @GetMapping("/search/{field}")
    public Page<ContactResponseDTO> getContactsByUserUidWithSearch(
            @PathVariable @Size(min = 3, message = "Search field must contain at least 3 characters.") String field,
            Pageable pageable) {
        User user = userService.getLoggedUser();
        return contactsService.getAllByField(user.getUid(), field, pageable);
    }

    @PostMapping
    public ResponseEntity saveContact(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        contactsService.saveByUser(contactRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    public ResponseEntity importContactsFromFile(@RequestParam("file") MultipartFile file) throws FileEmptyException {
        return contactsImportService.importContactsFromFile(file);
    }

    @PutMapping("/{contactUid}")
    public ResponseEntity updateContact(@PathVariable UUID contactUid, @Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        User user = userService.getLoggedUser();
        contactsService.update(contactUid, contactRequestDTO, user.getUid());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{contactUid}")
    public void deleteContactByUid(@PathVariable UUID contactUid) {
        User user = userService.getLoggedUser();
        contactsService.deleteByUid(contactUid, user.getUid());
    }

}
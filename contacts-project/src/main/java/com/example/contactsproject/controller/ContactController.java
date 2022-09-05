package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactRequestDTO;
import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.exceptions.FileEmptyException;
import com.example.contactsproject.controller.interfaces.GlobalResponseDefinition;
import com.example.contactsproject.service.services.ContactService;
import com.example.contactsproject.service.services.ContactsImportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequestMapping("/user/contacts")
@Tag(name = "Contact controller", description = "Methods for manipulating contacts. For user only.")
@RequiredArgsConstructor
@Validated
@PreAuthorize("authentication.principal.status == 'Verified'")
public class ContactController implements GlobalResponseDefinition {

    private final ContactService contactsService;

    private final ContactsImportService contactsImportService;

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> getAllContacts(Pageable pageable) {
        return ResponseEntity.ok(contactsService.getAllContactsByUser(pageable));
    }

    @GetMapping("/{contactUid}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable UUID contactUid) {
        return ResponseEntity.ok(contactsService.getByUid(contactUid));
    }

    @GetMapping("/search/{field}")
    public Page<ContactResponseDTO> getContactsByUserUidWithSearch(
            @PathVariable @Size(min = 3, message = "Search field must contain at least 3 characters.") String field,
            Pageable pageable) {
        return contactsService.getAllByField(field, pageable);
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
        contactsService.update(contactUid, contactRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{contactUid}")
    public void deleteContactByUid(@PathVariable UUID contactUid) {
        contactsService.deleteByUid(contactUid);
    }

}
package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contactType.ContactTypeRequestDTO;
import com.example.contactsproject.controller.dto.contactType.ContactTypeResponseDTO;
import com.example.contactsproject.controller.interfaces.GlobalResponseDefinition;
import com.example.contactsproject.service.serviceImpl.ContactTypeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/contactTypes")
@Tag(name = "Contact type controller", description = "Methods for manipulating contact types. For admin only.")
@RequiredArgsConstructor
public class ContactTypeController implements GlobalResponseDefinition {

    private final ContactTypeServiceImpl contactTypeService;

    @GetMapping
    public ResponseEntity<List<ContactTypeResponseDTO>> getAllContactTypes() {
        return ResponseEntity.ok(contactTypeService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ContactTypeResponseDTO> getContactTypeByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(contactTypeService.getByUid(uid));
    }

    @PostMapping
    public ResponseEntity saveContactType(@Valid @RequestBody ContactTypeRequestDTO contactTypeRequestDTO) {
        contactTypeService.save(contactTypeRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity updateContactType(@PathVariable UUID uid, @Valid @RequestBody ContactTypeRequestDTO contactTypeRequestDTO) {
        contactTypeService.update(uid, contactTypeRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}")
    public void deleteContactTypeById(@PathVariable UUID uid) {
        contactTypeService.deleteByUid(uid);
    }

}
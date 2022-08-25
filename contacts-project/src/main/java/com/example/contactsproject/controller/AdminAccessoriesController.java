package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.interfaces.GlobalResponseDefinition;
import com.example.contactsproject.service.serviceImpl.ContactServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@RestController
@RequestMapping("/admin/search/")
@Tag(name = "Admin accessories controller", description = "Method for searching contacts. For admin only.")
@RequiredArgsConstructor
@Validated
public class AdminAccessoriesController implements GlobalResponseDefinition {

    private final ContactServiceImpl contactService;

    @GetMapping("/contacts/{field}")
    public Page<ContactResponseDTO> getContactsWithSearch(
            @PathVariable @Size(min = 3, message = "Search field must contain at least 3 characters.") String field,
            Pageable pageable) {
        return contactService.getAllByFieldAdmin(field, pageable);
    }

}

package com.example.contactsproject.controller.dto.contactType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactTypeResponseDTO {

    private String typeName;
    private UUID uid;

}

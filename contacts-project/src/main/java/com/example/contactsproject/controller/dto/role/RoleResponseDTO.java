package com.example.contactsproject.controller.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleResponseDTO {

    private String type;
    private UUID uid;

}
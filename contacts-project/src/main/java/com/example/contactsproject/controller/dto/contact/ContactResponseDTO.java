package com.example.contactsproject.controller.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {

    private UUID uid;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String contactType;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

}
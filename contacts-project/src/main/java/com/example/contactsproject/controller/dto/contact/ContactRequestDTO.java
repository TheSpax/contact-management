package com.example.contactsproject.controller.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UUID contactTypeUid;

}
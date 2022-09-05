package com.example.contactsproject.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private UUID uid;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String status;

}
package com.example.contactsproject.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private UUID roleUid;

}

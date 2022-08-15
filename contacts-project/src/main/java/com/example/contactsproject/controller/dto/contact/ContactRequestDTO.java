package com.example.contactsproject.controller.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {

    @NotBlank(message = "firstName must not be blank!")
    @Size(max = 30, message = "firstName must not contain more than 30 characters")
    private String firstName;

    @NotBlank(message = "lastName must not be blank!")
    @Size(max = 30, message = "lastName must not contain more than 30 characters")
    private String lastName;

    @NotBlank(message = "email must not be blank!")
    @Email(message = "incorrect email format!")
    private String email;

    @NotBlank(message = "phoneNumber must not be blank!")
    private String phoneNumber;

    @NotNull(message = "contactTypeUid must not be blank!")
    private UUID contactTypeUid;

}
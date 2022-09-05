package com.example.contactsproject.controller.dto.user;

import com.example.contactsproject.entity.validators.ValidPassword;
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
public class UserRequestDTO {

    @NotBlank(message = "firstName must not be blank!")
    @Size(max = 30, message = "firstName must not contain more than 30 characters")
    private String firstName;

    @NotBlank(message = "lastName must not be blank!")
    @Size(max = 30, message = "lastName must not contain more than 30 characters")
    private String lastName;

    @NotBlank(message = "username must not be blank!")
    @Size(max = 30, message = "username must not contain more than 30 characters")
    private String username;

    @NotBlank(message = "email must not be blank")
    @Email(message = "incorrect email format!")
    private String email;

    @NotBlank(message = "password must not be blank!")
    @ValidPassword
    private String password;

    @NotNull(message = "uuid must not be blank!")
    private UUID roleUid;

    @NotBlank(groups = Update.class)
    private String phoneNumber;

    public interface Create {
    }

    public interface Update {
    }

}
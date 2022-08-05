package com.example.contactsproject.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String email;

}

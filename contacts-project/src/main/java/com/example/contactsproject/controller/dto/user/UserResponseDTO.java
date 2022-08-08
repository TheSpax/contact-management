package com.example.contactsproject.controller.dto.user;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import lombok.*;

import java.util.List;
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
    List<ContactResponseDTO> contactsList;

}

package com.example.contactsproject.controller.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {

    @NotBlank(message = "type must not be blank!")
    private String type;

}

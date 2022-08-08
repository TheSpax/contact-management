package com.example.contactsproject.controller.dto.contactType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ContactTypeRequestDTO {

    @NotBlank(message = "typeName must not be blank!")
    private String typeName;

}

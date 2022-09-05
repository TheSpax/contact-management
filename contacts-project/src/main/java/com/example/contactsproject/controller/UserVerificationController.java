package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/verification")
@RequiredArgsConstructor
public class UserVerificationController {

    private final UserService userService;

    @GetMapping("/verify")
    public void sendVerificationMessage() {
        userService.sendVerificationMessage();
    }

    @GetMapping("/verify/{code}")
    public void verify(@PathVariable String code) {
        userService.verify(code);
    }

    @PutMapping("/update-profile")
    public void updateProfile(@RequestBody @Validated(UserRequestDTO.Update.class) UserRequestDTO userRequestDTO) {
        userService.userProfileUpdate(userRequestDTO);
    }

}
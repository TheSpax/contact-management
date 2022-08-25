package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.controller.interfaces.GlobalResponseDefinition;
import com.example.contactsproject.service.serviceImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
@Tag(name = "User controller", description = "Methods for manipulating users. For admin only.")
@RequiredArgsConstructor
public class UserController implements GlobalResponseDefinition {

    private final UserServiceImpl userService;

    @GetMapping("")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<UserResponseDTO> getUserByUid(@PathVariable UUID userUid) {
        return ResponseEntity.ok(userService.getByUid(userUid));
    }

    @PostMapping()
    public ResponseEntity saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity updateUser(@PathVariable UUID uid, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.update(uid, userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userUid}")
    public void deleteUserByUid(@PathVariable UUID userUid) {
        userService.deleteByUid(userUid);
    }

}
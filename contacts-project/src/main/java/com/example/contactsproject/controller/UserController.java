package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.contact.ContactResponseDTO;
import com.example.contactsproject.controller.dto.user.UserRequestDTO;
import com.example.contactsproject.controller.dto.user.UserResponseDTO;
import com.example.contactsproject.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<UserResponseDTO> getUserByUid(@PathVariable UUID userUid) {
        return ResponseEntity.ok(userService.getByUid(userUid));
    }

//    @GetMapping("/{uid}/contacts")
//    public Page<ContactResponseDTO> getContactsByUserUid(@PathVariable UUID uid, Pageable pageable) {
//        return userService.getAllContactsByUserUid(uid, pageable);
//    }

//    @GetMapping("/{uid}/contacts/{field}")
//    public Page<ContactResponseDTO> getContactsByUserUidWithSearch(@PathVariable UUID uid, @PathVariable String field, Pageable pageable) {
//        return userService.getAllByUserUidAndField(uid, field, pageable);
//    }

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
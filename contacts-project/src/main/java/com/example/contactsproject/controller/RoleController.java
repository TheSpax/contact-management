package com.example.contactsproject.controller;

import com.example.contactsproject.entity.Role;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles/v1")
@RequiredArgsConstructor
public class RoleController {

    private final GenericService<Role> roleService;

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Role> getRoleByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(roleService.getByUid(uid));
    }

    @PostMapping
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.save(role));
    }

    @PutMapping
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.update(role));
    }

    @DeleteMapping("/{uid}")
    public void deleteRoleByUid(@PathVariable UUID uid) {
        roleService.deleteByUid(uid);
    }

}

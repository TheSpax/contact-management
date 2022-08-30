package com.example.contactsproject.controller;

import com.example.contactsproject.controller.dto.role.RoleRequestDTO;
import com.example.contactsproject.controller.dto.role.RoleResponseDTO;
import com.example.contactsproject.controller.interfaces.GlobalResponseDefinition;
import com.example.contactsproject.service.services.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/roles")
@Tag(name = "Role controller", description = "Methods for manipulating roles. For admin only.")
@RequiredArgsConstructor
public class RoleController implements GlobalResponseDefinition {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RoleResponseDTO> getRoleByUid(@PathVariable UUID uid) {
        return ResponseEntity.ok(roleService.getByUid(uid));
    }

    @PostMapping
    public ResponseEntity saveRole(@Valid @RequestBody RoleRequestDTO roleRequestDTO) {
        roleService.save(roleRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{uid}")
    public ResponseEntity updateRole(@PathVariable UUID uid, @Valid @RequestBody RoleRequestDTO roleRequestDTO) {
        roleService.update(uid, roleRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}")
    public void deleteRoleByUid(@PathVariable UUID uid) {
        roleService.deleteByUid(uid);
    }

}

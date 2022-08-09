package com.example.contactsproject.service.mappers;

import com.example.contactsproject.controller.dto.role.RoleRequestDTO;
import com.example.contactsproject.controller.dto.role.RoleResponseDTO;
import com.example.contactsproject.entity.Role;
import com.example.contactsproject.service.serviceImpl.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final RoleRepository roleRepository;

    public List<RoleResponseDTO> mapAllRolesToRoleDTO(List<Role> roles) {
        List<RoleResponseDTO> roleResponseDTOS = new ArrayList<>();
        for(Role r : roles) {
            RoleResponseDTO roleResponseDTO = mapRoleToRoleDTO(r);
            roleResponseDTOS.add(roleResponseDTO);
        }
        return roleResponseDTOS;
    }

    public RoleResponseDTO mapRoleToRoleDTO(Role role) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setUid(role.getUid());
        roleResponseDTO.setType(role.getType());
        return roleResponseDTO;
    }

    public Role mapRoleFromRoleDTO(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setUid(UUID.randomUUID());
        role.setType(roleRequestDTO.getType());
        return role;
    }

    public Role mapRoleFromRoleDTOUpdate(UUID uid, RoleRequestDTO roleRequestDTO) {
        Role role = roleRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setType(roleRequestDTO.getType());
        return role;
    }

}

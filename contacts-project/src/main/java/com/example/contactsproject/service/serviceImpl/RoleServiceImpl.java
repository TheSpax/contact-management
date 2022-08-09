package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.role.RoleRequestDTO;
import com.example.contactsproject.controller.dto.role.RoleResponseDTO;
import com.example.contactsproject.entity.Role;
import com.example.contactsproject.service.serviceImpl.repository.RoleRepository;
import com.example.contactsproject.service.mappers.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleResponseDTO> getAll() {
        return roleMapper.mapAllRolesToRoleDTO(roleRepository.findAll());
    }

    public RoleResponseDTO getByUid(UUID uid) {
        return roleMapper.mapRoleToRoleDTO(roleRepository.findByUid(uid).orElseThrow(() -> new EntityNotFoundException("Role not found")));
    }

    public void save(RoleRequestDTO roleRequestDTO) {
        Role role = roleMapper.mapRoleFromRoleDTO(roleRequestDTO);
        roleRepository.save(role);
    }

    public void update(UUID uid, RoleRequestDTO roleRequestDTO) {
        Role role = roleMapper.mapRoleFromRoleDTOUpdate(uid, roleRequestDTO);
        roleRepository.save(role);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        roleRepository.deleteByUid(uid);
    }
}

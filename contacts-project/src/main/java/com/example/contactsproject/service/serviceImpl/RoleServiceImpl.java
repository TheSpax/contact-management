package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.controller.dto.role.RoleRequestDTO;
import com.example.contactsproject.controller.dto.role.RoleResponseDTO;
import com.example.contactsproject.entity.Role;
import com.example.contactsproject.repository.RoleRepository;
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

    @Transactional(readOnly = true)
    public List<RoleResponseDTO> getAll() {
        return roleMapper.mapAllRolesToRoleDTO(roleRepository.findAll());
    }

    @Transactional(readOnly = true)
    public RoleResponseDTO getByUid(UUID roleUid) {
        return roleMapper.mapRoleToRoleDTO(this.getRole(roleUid));
    }

    @Transactional
    public void save(RoleRequestDTO roleRequestDTO) {
        Role role = roleMapper.mapRoleFromRoleDTO(roleRequestDTO);
        roleRepository.save(role);
    }

    @Transactional
    public void update(UUID roleUid, RoleRequestDTO roleRequestDTO) {
        Role role = this.getRole(roleUid);
        role = roleMapper.mapRoleFromRoleDTOUpdate(role, roleRequestDTO);
        roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public Role getRole(UUID roleUid) {
        return roleRepository.findByUid(roleUid).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        roleRepository.deleteByUid(uid);
    }
}

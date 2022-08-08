package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.Role;
import com.example.contactsproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {

    private final RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getByUid(UUID uid) {
        return roleRepository.findByUid(uid);
    }

    public Role save(Role role) {
        role.setUid(UUID.randomUUID());
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void deleteByUid(UUID uid) {
        roleRepository.deleteByUid(uid);
    }
}

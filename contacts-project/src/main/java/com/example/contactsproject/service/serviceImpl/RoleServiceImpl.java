package com.example.contactsproject.service.serviceImpl;

import com.example.contactsproject.entity.Role;
import com.example.contactsproject.repository.RoleRepository;
import com.example.contactsproject.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements GenericService<Role> {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getByUid(UUID uid) {
        return roleRepository.findByUid(uid);
    }

    @Override
    public Role save(Role role) {
        role.setUid(UUID.randomUUID());
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteByUid(UUID uid) {
        roleRepository.deleteByUid(uid);
    }
}

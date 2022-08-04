package com.example.contactsproject.service;

import com.example.contactsproject.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role saveRole(Role role);

    Role updateRole(Role role);

    void deleteRoleById(Long id);

}
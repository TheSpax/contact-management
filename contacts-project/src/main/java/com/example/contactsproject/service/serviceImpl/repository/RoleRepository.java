package com.example.contactsproject.service.serviceImpl.repository;

import com.example.contactsproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByUid(UUID uid);

    void deleteByUid(UUID uid);

}
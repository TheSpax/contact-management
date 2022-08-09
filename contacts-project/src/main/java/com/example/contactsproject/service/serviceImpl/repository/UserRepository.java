package com.example.contactsproject.service.serviceImpl.repository;

import com.example.contactsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(UUID uid);

    void deleteByUid(UUID uid);

}

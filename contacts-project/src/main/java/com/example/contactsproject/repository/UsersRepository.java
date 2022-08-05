package com.example.contactsproject.repository;

import com.example.contactsproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUid(UUID uid);

    void deleteByUid(UUID uid);

}

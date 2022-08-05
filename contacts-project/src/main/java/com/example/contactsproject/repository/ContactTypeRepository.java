package com.example.contactsproject.repository;

import com.example.contactsproject.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {

    ContactType findByUid(UUID uid);

    void deleteByUid(UUID uid);

}
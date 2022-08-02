package com.example.contactsproject.repository;

import com.example.contactsproject.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactType, Integer> {
}
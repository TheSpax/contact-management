package com.example.contactsproject.repository;

import com.example.contactsproject.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByUid(UUID uid);

    void deleteByUid(UUID uid);

    List<Contact> findAllByUser_Uid(UUID uid);

    Page<Contact> findAllByUser_Uid(UUID uid, Pageable pageable);

}

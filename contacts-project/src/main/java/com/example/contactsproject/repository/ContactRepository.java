package com.example.contactsproject.repository;

import com.example.contactsproject.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByUid(UUID uid);

    Optional<Contact> findByUidAndUser_Uid(UUID contactUid, UUID userUid);

    void deleteByUid(UUID uid);

    Page<Contact> findAllByUser_Uid(UUID uid, Pageable pageable);

    @Query(value = "SELECT * FROM contacts JOIN users ON contacts.user_id = users.id " +
            "WHERE users.uid = :userUid AND (contacts.first_name LIKE CONCAT('%', :field, '%') " +
            "OR contacts.last_name LIKE CONCAT('%', :field, '%')" +
            "OR contacts.email LIKE CONCAT('%', :field, '%')" +
            "OR phone_number LIKE CONCAT('%', :field, '%'))", nativeQuery = true)
    Page<Contact> findByFieldPassedAndUser(String field, UUID userUid, Pageable pageable);

    @Query(value = "SELECT * FROM contacts " +
            "WHERE (contacts.first_name LIKE CONCAT('%', :field, '%')" +
            "OR contacts.last_name LIKE CONCAT('%', :field, '%')" +
            "OR contacts.email LIKE CONCAT('%', :field, '%')" +
            "OR phone_number LIKE CONCAT('%', :field, '%'))", nativeQuery = true)
    Page<Contact> findByField(String field, Pageable pageable);

}

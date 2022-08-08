package com.example.contactsproject.repository;

import com.example.contactsproject.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    Contacts findByUid(UUID uid);

    void deleteByUid(UUID uid);

    List<Contacts> findAllByUser_Uid(UUID uid);

}

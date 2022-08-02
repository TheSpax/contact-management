package com.example.contactsproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact_type")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "type_name")
    private String typeName;

}
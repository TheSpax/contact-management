package com.example.contactsproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

}

package com.example.contactsproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

}
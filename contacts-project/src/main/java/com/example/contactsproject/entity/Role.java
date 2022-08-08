package com.example.contactsproject.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "type must not be null")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "uid must not be null")
    @Column(name = "uid", unique = true, updatable = false)
    private UUID uid;

}
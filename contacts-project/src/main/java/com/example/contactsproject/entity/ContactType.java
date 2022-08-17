package com.example.contactsproject.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "contact_types")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, message = "typeName must not contain more than 30 characters")
    @NotBlank(message = "typeName must not be blank")
    @Column(name = "type_name")
    private String typeName;

    @Column(name = "time_created", updatable = false)
    @CreationTimestamp
    private LocalDateTime timeCreated;

    @Column(name = "time_updated")
    @UpdateTimestamp
    private LocalDateTime timeUpdated;

    @NotNull(message = "typeName must not be blank")
    @Column(name = "uid", unique = true, updatable = false)
    private UUID uid;

}
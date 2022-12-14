package com.example.contactsproject.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, message = "firstName must not contain more than 30 characters")
    @NotBlank(message = "firstName must not be blank")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 30, message = "lastName must not contain more than 30 characters")
    @NotBlank(message = "lastName must not be blank")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be in a valid format")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "phoneNumber must not be blank")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private ContactType contactType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time_created", updatable = false)
    @CreationTimestamp
    private LocalDateTime timeCreated;

    @Column(name = "time_updated")
    @UpdateTimestamp
    private LocalDateTime timeUpdated;

    @NotNull(message = "uid must not be blank")
    @Column(name = "uid", unique = true, updatable = false)
    private UUID uid;

}

package com.example.contactsproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @Column(name = "time_updated")
    private LocalDateTime timeUpdated;

}

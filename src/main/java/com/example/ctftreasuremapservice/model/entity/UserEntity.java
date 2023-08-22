package com.example.ctftreasuremapservice.model.entity;

import jakarta.persistence.*;

import java.util.UUID;


@Table(name = "user_tabel")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String password;
}
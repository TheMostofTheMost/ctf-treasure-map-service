package com.example.ctftreasuremapservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class User {

    @Id
    @Column
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;
    @Column
    String name;

//конструктор, геттеры, сеттеры
}

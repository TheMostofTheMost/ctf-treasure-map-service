package com.example.ctftreasuremapservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class JwtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String jwtToken;
    @Builder
    public JwtEntity(String username, String jwtToken) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.id = UUID.randomUUID();
    }

}

package com.example.ctftreasuremapservice.repository;

import com.example.ctftreasuremapservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> getUserByUsername(String username);
}

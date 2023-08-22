package com.example.ctftreasuremapservice.repository;

import com.example.ctftreasuremapservice.model.entity.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JwtRepo extends JpaRepository<JwtEntity, String> {
    @Modifying
    @Transactional
    JwtEntity save(JwtEntity jwtEntity);

}

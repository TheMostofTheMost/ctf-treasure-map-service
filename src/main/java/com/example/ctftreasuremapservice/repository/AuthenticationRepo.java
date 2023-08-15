package com.example.ctftreasuremapservice.repository;

import com.example.ctftreasuremapservice.model.entity.UserEntity;
import com.example.ctftreasuremapservice.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepo extends JpaRepository<UserEntity, String> {


    @Query("select user from UserEntity user where user.name = :name and user.password = :password")
    boolean findByNameAndPassword(String name, String password);
}

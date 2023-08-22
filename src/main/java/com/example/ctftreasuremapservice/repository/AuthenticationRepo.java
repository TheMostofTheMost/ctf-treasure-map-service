package com.example.ctftreasuremapservice.repository;

import com.example.ctftreasuremapservice.model.entity.UserEntity;
import com.example.ctftreasuremapservice.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepo extends JpaRepository<UserEntity, String> {

    @Query(value = "SELECT user FROM UserEntity user WHERE user.name = :name AND user.password = :password")
    UserEntity findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}

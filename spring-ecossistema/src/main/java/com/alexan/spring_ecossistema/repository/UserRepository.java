package com.alexan.spring_ecossistema.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexan.spring_ecossistema.repository.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String username);

    UserEntity findByFullName(String fullName);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.profile LEFT JOIN FETCH u.loginAttempts WHERE u IN :users")
    List<UserEntity> findAllWithProfileAndAttempts(List<UserEntity> users);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.loginAttempts WHERE u.email = :email")
    Optional<UserEntity> findByEmailWithAttempts(@Param("email") String email);
}

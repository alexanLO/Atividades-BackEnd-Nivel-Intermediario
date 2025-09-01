package com.alexan.spring_ecossistema.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexan.spring_ecossistema.repository.entity.UserEntity;
import com.alexan.spring_ecossistema.repository.projections.UserSummaryProjection;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByEmail(String username);

    UserEntity findByFullName(String fullName);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.profile LEFT JOIN FETCH u.loginAttempts WHERE u IN :users")
    List<UserEntity> findAllWithProfileAndAttempts(List<UserEntity> users);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.loginAttempts WHERE u.email = :email")
    Optional<UserEntity> findByEmailWithAttempts(@Param("email") String email);

    @Query("SELECT u FROM UserEntity u WHERE u.status = 'ATIVO' AND SIZE(u.loginAttempts) = 0")
    List<UserEntity> findAllNewUser();

    // * Criado apenas para atividade ainda não tem uso especifico */
    @Query("SELECT u FROM UserEntity u ORDER BY u.createAt DESC")
    List<UserEntity> findLatestRegistered(Pageable pageable);

    Optional<UserSummaryProjection> findProjectedById(UUID id);

    List<UserSummaryProjection> findProjectedAllBy(Pageable pageable);
}

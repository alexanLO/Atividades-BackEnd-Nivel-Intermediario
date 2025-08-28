package com.alexan.spring_ecossistema.repository.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loginsAttempts")
public class LoginAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "attTime")
    private LocalDateTime attemptTime;

    // * Essa coluna é um boolean, mas como usamos oracle db ele não aceita true */
    // * ou false então deve se passar 0 para false e 1 para true */
    @Column(name = "success")
    private String success;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userId")
    private UserEntity user;
}

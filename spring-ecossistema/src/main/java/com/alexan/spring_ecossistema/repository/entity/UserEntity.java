package com.alexan.spring_ecossistema.repository.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.alexan.spring_ecossistema.model.enums.StatusEnum;
import com.alexan.spring_ecossistema.model.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "fName", length = 150, nullable = false)
    private String fullName;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "passwd", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10, nullable = false)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sttus", length = 10, nullable = false)
    private StatusEnum status;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createAt;

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    private ProfileEntity profile;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<LoginAttemptEntity> loginAttempts;
}

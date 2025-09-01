package com.alexan.spring_ecossistema.repository.especification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alexan.spring_ecossistema.model.UserFilter;
import com.alexan.spring_ecossistema.repository.entity.UserEntity;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class UserEspecification {

    // * O certo é cria consultas separadas para cada variavel e montar a query no
    // * service ou o lugar que está persistindo, mas quero otimizar o conteudo de
    // * esstudo e fiz como se fosse um criteria API */
    public static Specification<UserEntity> byFilter(UserFilter request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getFullName() != null) {
                predicates.add(cb.equal(root.get("fullName"), request.getFullName()));
            }
            if (request.getEmail() != null) {
                predicates.add(cb.equal(root.get("email"), request.getEmail()));
            }
            if (request.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), request.getStatus()));
            }
            if (request.getCreateBy() != null && request.getCreateUntil() != null) {
                predicates.add(cb.between(root.get("createAt"), request.getCreateBy(), request.getCreateUntil()));
            } else {
                if (request.getCreateBy() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createAt"), request.getCreateBy()));
                }
                if (request.getCreateUntil() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createAt"), request.getCreateUntil()));
                }
            }

            // fetch para evitar LazyInitializationException
            root.fetch("loginAttempts", JoinType.LEFT);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

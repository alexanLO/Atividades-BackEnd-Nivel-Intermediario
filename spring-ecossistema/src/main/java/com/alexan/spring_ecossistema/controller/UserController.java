package com.alexan.spring_ecossistema.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.config.RequestInterceptor;
import com.alexan.spring_ecossistema.model.dto.AlterarUser;
import com.alexan.spring_ecossistema.model.dto.User;
import com.alexan.spring_ecossistema.service.UserService;
import com.alexan.spring_ecossistema.validator.annotations.Auditable;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    private UserService service;

    @PostMapping
    @Auditable(action = "Registering a new user")
    public ResponseEntity<Void> register(@RequestBody @Valid User request) {
        log.info("Registering a new user.");

        service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        log.info("Searching for all users");
        return ResponseEntity.status(HttpStatus.OK).body(service.listing());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        log.info("Searching user by id: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody @Valid AlterarUser request) {
        log.info("updating user data: id = " + id);

        service.updateUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @PathVariable String status) {
        log.info("updating user status: id = " + id + ", new status: " + status);

        service.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("deleting user: " + id);

        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

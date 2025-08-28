package com.alexan.spring_ecossistema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.responses.SystemInfoResponse;
import com.alexan.spring_ecossistema.service.impl.InfoServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/info")
public class InfoController {

    private final InfoServiceImpl service;

    @GetMapping
    public ResponseEntity<SystemInfoResponse> getInfo() {
        log.info("Fornecendo informacoes do sistema: ");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getInfo());
    }

}

package com.alexan.spring_ecossistema.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexan.spring_ecossistema.controller.dto.responses.SystemInfoResponse;
import com.alexan.spring_ecossistema.service.impl.InfoServiceImpl;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Autowired
    private InfoServiceImpl service;
    private Logger LOGGER = LoggerFactory.getLogger(InfoController.class);

    @GetMapping
    public ResponseEntity<SystemInfoResponse> getInfo() {
        LOGGER.info("Fornecendo informacoes do sistema: ");

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getInfo());
    }

}

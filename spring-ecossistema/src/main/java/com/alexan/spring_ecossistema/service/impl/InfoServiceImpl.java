package com.alexan.spring_ecossistema.service.impl;

import java.lang.ProcessHandle.Info;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import com.alexan.spring_ecossistema.controller.dto.responses.SystemInfoResponse;
import com.alexan.spring_ecossistema.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private BuildProperties buildProperties;

    public SystemInfoResponse getInfo() {

        try {
            return new SystemInfoResponse(buildProperties.getName(),
                    buildProperties.getVersion(),
                    System.getProperty("java.version"),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                    InetAddress.getLocalHost().getHostAddress());

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }
}

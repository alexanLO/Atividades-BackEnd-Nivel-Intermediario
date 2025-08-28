package com.alexan.spring_ecossistema.controller.dto.responses;

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
public class SystemInfoResponse {

    private String applicationName;
    private String appVersion;
    private String javaVersion;
    private String currentTime;
    private String serverIp;
}

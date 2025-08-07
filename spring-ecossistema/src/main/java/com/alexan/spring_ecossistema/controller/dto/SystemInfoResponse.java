package com.alexan.spring_ecossistema.controller.dto;

public class SystemInfoResponse {

    private String applicationName;
    private String appVersion;
    private String javaVersion;
    private String currentTime;
    private String serverIp;

    public SystemInfoResponse() {
    }

    public SystemInfoResponse(String applicationName, String appVersion, String javaVersion, String currentTime,
            String serverIp) {
        this.applicationName = applicationName;
        this.appVersion = appVersion;
        this.javaVersion = javaVersion;
        this.currentTime = currentTime;
        this.serverIp = serverIp;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getServerIp() {
        return serverIp;
    }
}

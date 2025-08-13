package com.alexan.spring_ecossistema.aop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit {

    private String nameUser;
    private String actionExecuted;
    private String entityAffected;
    private Long idEntityAffected;
    private String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    public Audit(String nameUser, String actionExecuted, String entityAffected, Long idEntityAffected,
            String dateTime) {
        this.nameUser = nameUser;
        this.actionExecuted = actionExecuted;
        this.entityAffected = entityAffected;
        this.idEntityAffected = idEntityAffected;
        this.dateTime = dateTime;
    }

    public Audit(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getActionExecuted() {
        return actionExecuted;
    }

    public String getEntityAffected() {
        return entityAffected;
    }

    public Long getIdEntityAffected() {
        return idEntityAffected;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "usuario='" + nameUser + '\'' +
                ", acao='" + actionExecuted + '\'' +
                ", entidade='" + entityAffected + '\'' +
                ", idEntidade='" + idEntityAffected + '\'' +
                ", dataHora=" + dateTime +
                '}';
    }
}

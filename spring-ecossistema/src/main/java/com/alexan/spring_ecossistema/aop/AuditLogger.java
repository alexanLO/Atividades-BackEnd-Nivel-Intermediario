package com.alexan.spring_ecossistema.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditLogger {
    
    private Logger LOGGER = LoggerFactory.getLogger(AuditLogger.class);

    public void log(Audit audit) {
     
        LOGGER.info(audit.toString());
    }
}

package com.alexan.spring_ecossistema.aop;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexan.spring_ecossistema.validator.annotations.Auditable;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditLogger auditLogger;
    private Logger log = LoggerFactory.getLogger(AuditAspect.class);

    @Before("@annotation(auditable)")
    public void logBefore(JoinPoint jPoint, Auditable auditable) {
        log.info("Iniciando auditoria: ");

        log.info("Solicitante: {}\n" + "Nome da classe: {}|\n" + "Nome do metodo: {}|\n" + "Acao: {}",
                new Audit(getLoggedUser()).getNameUser(),
                jPoint.getTarget().getClass().getSimpleName(),
                jPoint.getSignature().getName(),
                auditable.action());
    }

    @Around("@annotation(auditable)")
    public Object logAround(ProceedingJoinPoint pJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pJoinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;

        log.info("Tempo de execucao: {} ms", elapsedTime);
        return result;
    }

    @AfterReturning(pointcut = "@annotation(auditable)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Auditable auditable, Object result) {
        String id = extractId(result);

        log.info("Usuario afetado: {}\nID afetado: {}",
                result != null ? result.getClass().getSimpleName() : "null",
                id);

        log.info("Acao de CRUD concluida com retorno: {}", result);

        Audit audit = new Audit(
                getLoggedUser(),
                auditable.action(),
                result != null ? result.getClass().getSimpleName() : "null",
                tryParseId(id),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        auditLogger.log(audit);
    }

    private String getLoggedUser() {
        return "admin";
    }

    private String extractId(Object obj) {
        if (obj == null)
            return "sem id";
        try {
            Method method = obj.getClass().getMethod("getId");
            Object id = method.invoke(obj);
            return id != null ? id.toString() : "sem id";
        } catch (Exception e) {
            return "sem id";
        }
    }
    /**
     * Tenta converter uma String para Long, retornando null se falhar.
     *
     * @param id a String a ser convertida
     * @return o Long convertido ou null se a conversão falhar
     */      
    private Long tryParseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }
    }
}

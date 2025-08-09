package com.alexan.spring_ecossistema.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {

        request.setAttribute("startTime", System.currentTimeMillis());
        log.info("[INICIO]" + "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "] "
                + request.getRequestURI() + " " + request.getMethod());

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler, @Nullable Exception ex) throws Exception {

        try {
            Long startTime = (Long) request.getAttribute("startTime");
            Long duration = System.currentTimeMillis() - startTime;
            log.info("[FIM]" + "[Status: " + response.getStatus() + "] [" + duration + "ms]");

        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}

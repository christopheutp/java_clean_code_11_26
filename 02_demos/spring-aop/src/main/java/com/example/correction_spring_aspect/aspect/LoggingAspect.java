package com.example.correction_spring_aspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // Le pointcut sélectionne uniquement les méthodes annotées @Log.
    @Pointcut("@annotation(com.example.correction_spring_aspect.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object logAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        LOGGER.info("Début : {}", method);
        try {
            // Exécute la méthode métier et conserve son résultat ou son exception.
            return joinPoint.proceed();
        } finally {
            LOGGER.info("Fin : {}", method);
        }
    }
}

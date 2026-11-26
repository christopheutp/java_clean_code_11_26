package com.example.correction_spring_aspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("@annotation(com.example.correction_spring_aspect.annotation.Performance)")
    public void performancePointCut() {
    }

    @Around("performancePointCut()")
    public Object performanceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            // Mesure une durée, même lorsque la méthode échoue.
            LOGGER.info("{} : {} ms", joinPoint.getSignature().getName(), (System.nanoTime() - start) / 1_000_000.0);
        }
    }
}

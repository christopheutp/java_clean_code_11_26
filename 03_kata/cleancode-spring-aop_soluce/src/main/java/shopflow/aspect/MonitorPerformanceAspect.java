package shopflow.aspect;

import shopflow.monitoring.MetricsService;
import shopflow.annotation.MonitorPerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(30)
public class MonitorPerformanceAspect {
    @Autowired
    MetricsService metrics;

    @Around("@annotation(a)")
    public Object around(ProceedingJoinPoint context, MonitorPerformance a) throws Throwable {
        long start = System.nanoTime();
        try {
            return context.proceed();
        } finally {
            metrics.record(a.operation(), start, a.thresholdMs());
        }
    }
}

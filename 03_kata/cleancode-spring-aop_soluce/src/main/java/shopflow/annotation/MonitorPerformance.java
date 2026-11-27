package shopflow.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MonitorPerformance {
    String operation();

    long thresholdMs();
}

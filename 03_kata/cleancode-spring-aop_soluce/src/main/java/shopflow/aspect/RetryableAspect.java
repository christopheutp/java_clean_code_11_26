package shopflow.aspect;

import shopflow.annotation.Retryable;
import shopflow.exception.TemporaryPaymentException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(40)
public class RetryableAspect {

    @Around("@annotation(a)")
    public Object around(ProceedingJoinPoint context, Retryable a) throws Throwable {
        for (int attempt = 1; ; attempt++) {
            try {
                return context.proceed();
            } catch (TemporaryPaymentException e) {
                if (attempt >= a.maxAttempts()) throw e;
            }
        }
    }
}

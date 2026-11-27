package shopflow.aspect;

import shopflow.audit.AuditService;
import shopflow.annotation.Auditable;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(20)
public class AuditableAspect {
    @Autowired
    AuditService audit;

    @Around("@annotation(a)")
    public Object around(ProceedingJoinPoint context, Auditable a) throws Throwable {
        try {
            Object result = context.proceed();
            audit.record(a.value(), true);
            return result;
        } catch (Throwable e) {
            audit.record(a.value(), false);
            throw e;
        }
    }
}

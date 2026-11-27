package shopflow.aspect;

import shopflow.security.PermissionService;
import shopflow.annotation.RequiresPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(10)
public class RequiresPermissionAspect {
    @Autowired
    PermissionService permissions;

    @Around("@annotation(a)")
    public Object around(ProceedingJoinPoint context, RequiresPermission a) throws Throwable {
        permissions.check(a.value());
        return context.proceed();
    }
}

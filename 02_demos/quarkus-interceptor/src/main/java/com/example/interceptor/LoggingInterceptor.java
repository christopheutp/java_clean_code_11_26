package com.example.interceptor;

import com.example.annotation.Loggable;
import jakarta.annotation.Priority;
import jakarta.interceptor.*;
import org.jboss.logging.Logger;

@Loggable
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor {
    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class);

    // L'intercepteur encadre l'appel métier sans changer son résultat.
    @AroundInvoke
    public Object loggingMethodCall(InvocationContext context) throws Exception {
        String method = context.getMethod().getName();
        LOGGER.info("Début : " + method);
        try {
            return context.proceed();
        } finally {
            LOGGER.info("Fin : " + method);
        }
    }
}

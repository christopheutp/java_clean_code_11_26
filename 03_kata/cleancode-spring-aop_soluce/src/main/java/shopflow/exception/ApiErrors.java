package shopflow.exception;

import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class ApiErrors {
    @ExceptionHandler({BusinessException.class, ForbiddenException.class, TemporaryPaymentException.class, PermanentPaymentException.class})
    public ResponseEntity<Map<String, String>> handle(RuntimeException e) {
        return ResponseEntity.status(status(e)).body(Map.of("error", e.getMessage()));
    }

    static int status(RuntimeException e) {
        if (e instanceof ForbiddenException) return 403;
        if (e instanceof TemporaryPaymentException) return 503;
        if (e instanceof PermanentPaymentException) return 422;
        return 400;
    }
}

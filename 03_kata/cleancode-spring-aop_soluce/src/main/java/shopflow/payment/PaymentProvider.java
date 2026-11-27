package shopflow.payment;

import shopflow.exception.BusinessException;
import shopflow.exception.PermanentPaymentException;
import shopflow.exception.TemporaryPaymentException;
import org.springframework.stereotype.Service;

import java.util.*;

import shopflow.model.Models.PaymentConfig;

@Service
public class PaymentProvider {
    private int failures, attempts;
    private boolean permanent;
    private final Set<String> completed = new HashSet<>();

    public void configure(PaymentConfig config) {
        if (config.temporaryFailures() < 0) throw new BusinessException("Invalid failures");
        failures = config.temporaryFailures();
        permanent = config.permanentFailure();
        attempts = 0;
    }

    public void execute(String key) {
        attempts++;
        if (completed.contains(key)) return;
        if (permanent) throw new PermanentPaymentException("Payment refused");
        if (failures > 0) {
            failures--;
            throw new TemporaryPaymentException("Provider unavailable");
        }
        completed.add(key);
    }

    public Map<String, Integer> stats() {
        return Map.of("attempts", attempts, "effects", completed.size());
    }

    public void reset() {
        failures = attempts = 0;
        permanent = false;
        completed.clear();
    }
}

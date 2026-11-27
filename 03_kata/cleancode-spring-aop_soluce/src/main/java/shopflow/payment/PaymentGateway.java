package shopflow.payment;

import shopflow.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentGateway {
    @Autowired
    PaymentProvider provider;

    @Retryable(maxAttempts = 3)
    public void pay(String id) {
        provider.execute("pay:" + id);
    }

    @Retryable(maxAttempts = 3)
    public void refund(String id) {
        provider.execute("refund:" + id);
    }
}

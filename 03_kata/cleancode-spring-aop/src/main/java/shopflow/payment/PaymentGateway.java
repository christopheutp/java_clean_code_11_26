package shopflow.payment;

import shopflow.exception.TemporaryPaymentException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PaymentGateway {
    @Autowired
    PaymentProvider provider;

    public void pay(String id) {
        for (int attempt = 1; ; attempt++) {
            try {
                provider.execute("pay:" + id);
                return;
            } catch (TemporaryPaymentException e) {
                if (attempt >= 3) throw e;
            }
        }
    }

    public void refund(String id) {
        for (int attempt = 1; ; attempt++) {
            try {
                provider.execute("refund:" + id);
                return;
            } catch (TemporaryPaymentException e) {
                if (attempt >= 3) throw e;
            }
        }
    }
}

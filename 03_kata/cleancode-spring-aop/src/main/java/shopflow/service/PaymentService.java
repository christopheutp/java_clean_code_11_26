package shopflow.service;

import shopflow.audit.AuditService;
import shopflow.exception.BusinessException;
import shopflow.monitoring.MetricsService;
import shopflow.payment.PaymentGateway;
import shopflow.security.PermissionService;
import shopflow.repository.Store;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import shopflow.model.Models.*;

@Service
public class PaymentService {
    @Autowired
    Store store;
    @Autowired
    PermissionService permissions;
    @Autowired
    AuditService audit;
    @Autowired
    MetricsService metrics;
    @Autowired
    PaymentGateway gateway;

    public Order pay(String id) {
        permissions.check("PAYMENT");
        long start = System.nanoTime();
        try {
            Order order = store.order(id);
            if (order.status() != Status.CONFIRMED) throw new BusinessException("Invalid payment status");
            gateway.pay(id);
            Order result = store.save(order.withStatus(Status.PAID));
            audit.record("PAY", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("PAY", false);
            throw e;
        } finally {
            metrics.record("PAY", start, 800);
        }
    }

    public Order refund(String id) {
        permissions.check("PAYMENT");
        long start = System.nanoTime();
        try {
            Order order = store.order(id);
            if (order.status() != Status.PAID) throw new BusinessException("Invalid payment status");
            gateway.refund(id);
            Order result = store.save(order.withStatus(Status.REFUNDED));
            audit.record("REFUND", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("REFUND", false);
            throw e;
        } finally {
            metrics.record("REFUND", start, 800);
        }
    }

}

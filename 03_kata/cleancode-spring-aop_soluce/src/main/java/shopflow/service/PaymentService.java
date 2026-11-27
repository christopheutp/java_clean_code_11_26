package shopflow.service;

import shopflow.annotation.Auditable;
import shopflow.exception.BusinessException;
import shopflow.annotation.MonitorPerformance;
import shopflow.payment.PaymentGateway;
import shopflow.annotation.RequiresPermission;
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
    PaymentGateway gateway;

    @RequiresPermission("PAYMENT")
    @Auditable("PAY")
    @MonitorPerformance(operation = "PAY", thresholdMs = 800)
    public Order pay(String id) {
        Order order = store.order(id);
        if (order.status() != Status.CONFIRMED) throw new BusinessException("Invalid payment status");
        gateway.pay(id);
        return store.save(order.withStatus(Status.PAID));
    }

    @RequiresPermission("PAYMENT")
    @Auditable("REFUND")
    @MonitorPerformance(operation = "REFUND", thresholdMs = 800)
    public Order refund(String id) {
        Order order = store.order(id);
        if (order.status() != Status.PAID) throw new BusinessException("Invalid payment status");
        gateway.refund(id);
        return store.save(order.withStatus(Status.REFUNDED));
    }

}

package shopflow.service;

import shopflow.audit.AuditService;
import shopflow.exception.BusinessException;
import shopflow.monitoring.MetricsService;
import shopflow.security.PermissionService;
import shopflow.repository.Store;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import shopflow.model.Models.*;

@Service
public class OrderService {
    @Autowired
    Store store;
    @Autowired
    PermissionService permissions;
    @Autowired
    AuditService audit;
    @Autowired
    MetricsService metrics;

    public Order create(CreateOrder input) {
        permissions.check("ORDER");
        long start = System.nanoTime();
        try {
            if (input.quantity() <= 0 || !store.hasCustomer(input.customerId()))
                throw new BusinessException("Invalid order");
            Product product = store.product(input.productId());
            int total;
            try {
                total = Math.multiplyExact(product.price(), input.quantity());
            } catch (ArithmeticException e) {
                throw new BusinessException("Total too large");
            }
            Order result = store.save(new Order(store.nextOrderId(), input.customerId(), product.id(), input.quantity(), total, Status.NEW));
            audit.record("CREATE_ORDER", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("CREATE_ORDER", false);
            throw e;
        } finally {
            metrics.record("CREATE_ORDER", start, 500);
        }
    }

    public Order confirm(String id) {
        permissions.check("ORDER");
        long start = System.nanoTime();
        try {
            Order order = store.order(id);
            if (order.status() != Status.NEW) throw new BusinessException("Order not new");
            int available = store.stock(order.productId());
            if (available < order.quantity()) throw new BusinessException("Insufficient stock");
            store.setStock(order.productId(), available - order.quantity());
            Order result = store.save(order.withStatus(Status.CONFIRMED));
            audit.record("CONFIRM_ORDER", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("CONFIRM_ORDER", false);
            throw e;
        } finally {
            metrics.record("CONFIRM_ORDER", start, 500);
        }
    }

    public Order cancel(String id) {
        permissions.check("ORDER");
        long start = System.nanoTime();
        try {
            Order order = store.order(id);
            if (order.status() != Status.NEW && order.status() != Status.CONFIRMED)
                throw new BusinessException("Cannot cancel");
            if (order.status() == Status.CONFIRMED) {
                try {
                    store.increaseStock(order.productId(), order.quantity());
                } catch (ArithmeticException e) {
                    throw new BusinessException("Stock too large");
                }
            }
            Order result = store.save(order.withStatus(Status.CANCELLED));
            audit.record("CANCEL_ORDER", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("CANCEL_ORDER", false);
            throw e;
        } finally {
            metrics.record("CANCEL_ORDER", start, 500);
        }
    }

    public Order get(String id) {
        return store.order(id);
    }

    public List<Order> list() {
        return store.orders();
    }
}

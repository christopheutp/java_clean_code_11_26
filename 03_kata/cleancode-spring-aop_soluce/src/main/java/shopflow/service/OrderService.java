package shopflow.service;

import shopflow.annotation.Auditable;
import shopflow.exception.BusinessException;
import shopflow.annotation.MonitorPerformance;
import shopflow.annotation.RequiresPermission;
import shopflow.repository.Store;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import shopflow.model.Models.*;

@Service
public class OrderService {
    @Autowired
    Store store;

    @RequiresPermission("ORDER")
    @Auditable("CREATE_ORDER")
    @MonitorPerformance(operation = "CREATE_ORDER", thresholdMs = 500)
    public Order create(CreateOrder input) {
        if (input.quantity() <= 0 || !store.hasCustomer(input.customerId()))
            throw new BusinessException("Invalid order");
        Product product = store.product(input.productId());
        int total;
        try {
            total = Math.multiplyExact(product.price(), input.quantity());
        } catch (ArithmeticException e) {
            throw new BusinessException("Total too large");
        }
        return store.save(new Order(store.nextOrderId(), input.customerId(), product.id(), input.quantity(), total, Status.NEW));
    }

    @RequiresPermission("ORDER")
    @Auditable("CONFIRM_ORDER")
    @MonitorPerformance(operation = "CONFIRM_ORDER", thresholdMs = 500)
    public Order confirm(String id) {
        Order order = store.order(id);
        if (order.status() != Status.NEW) throw new BusinessException("Order not new");
        int available = store.stock(order.productId());
        if (available < order.quantity()) throw new BusinessException("Insufficient stock");
        store.setStock(order.productId(), available - order.quantity());
        return store.save(order.withStatus(Status.CONFIRMED));
    }

    @RequiresPermission("ORDER")
    @Auditable("CANCEL_ORDER")
    @MonitorPerformance(operation = "CANCEL_ORDER", thresholdMs = 500)
    public Order cancel(String id) {
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
        return store.save(order.withStatus(Status.CANCELLED));
    }

    public Order get(String id) {
        return store.order(id);
    }

    public List<Order> list() {
        return store.orders();
    }
}

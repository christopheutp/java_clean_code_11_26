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
public class StockService {
    @Autowired
    Store store;
    @Autowired
    PermissionService permissions;
    @Autowired
    AuditService audit;
    @Autowired
    MetricsService metrics;

    public Integer restock(String id, int quantity) {
        permissions.check("STOCK");
        long start = System.nanoTime();
        try {
            store.product(id);
            if (quantity <= 0) throw new BusinessException("Invalid quantity");
            int total;
            try {
                total = Math.addExact(store.stock(id), quantity);
            } catch (ArithmeticException e) {
                throw new BusinessException("Stock too large");
            }
            store.setStock(id, total);
            Integer result = total;
            audit.record("RESTOCK", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("RESTOCK", false);
            throw e;
        } finally {
            metrics.record("RESTOCK", start, 300);
        }
    }

    public int get(String id) {
        store.product(id);
        return store.stock(id);
    }
}

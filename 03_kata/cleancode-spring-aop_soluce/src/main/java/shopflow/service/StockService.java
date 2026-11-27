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
public class StockService {
    @Autowired
    Store store;

    @RequiresPermission("STOCK")
    @Auditable("RESTOCK")
    @MonitorPerformance(operation = "RESTOCK", thresholdMs = 300)
    public Integer restock(String id, int quantity) {
        store.product(id);
        if (quantity <= 0) throw new BusinessException("Invalid quantity");
        int total;
        try {
            total = Math.addExact(store.stock(id), quantity);
        } catch (ArithmeticException e) {
            throw new BusinessException("Stock too large");
        }
        store.setStock(id, total);
        return total;
    }

    public int get(String id) {
        store.product(id);
        return store.stock(id);
    }
}

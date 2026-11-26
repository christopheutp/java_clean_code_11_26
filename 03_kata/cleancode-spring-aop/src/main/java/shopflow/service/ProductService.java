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
public class ProductService {
    @Autowired
    Store store;
    @Autowired
    PermissionService permissions;
    @Autowired
    AuditService audit;
    @Autowired
    MetricsService metrics;

    public Product add(Product product) {
        permissions.check("PRODUCT");
        long start = System.nanoTime();
        try {
            if (product.id() == null || product.id().isBlank() || product.name() == null || product.name().isBlank() || product.price() <= 0 || store.hasProduct(product.id()))
                throw new BusinessException("Invalid product");
            store.addProduct(product);
            Product result = product;
            audit.record("ADD_PRODUCT", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("ADD_PRODUCT", false);
            throw e;
        } finally {
            metrics.record("ADD_PRODUCT", start, 300);
        }
    }

    public Product price(String id, int price) {
        permissions.check("PRODUCT");
        long start = System.nanoTime();
        try {
            Product old = store.product(id);
            if (price <= 0) throw new BusinessException("Invalid price");
            Product product = new Product(id, old.name(), price);
            store.saveProduct(product);
            Product result = product;
            audit.record("CHANGE_PRICE", true);
            return result;
        } catch (RuntimeException e) {
            audit.record("CHANGE_PRICE", false);
            throw e;
        } finally {
            metrics.record("CHANGE_PRICE", start, 300);
        }
    }

    public List<Product> list() {
        return store.products();
    }
}

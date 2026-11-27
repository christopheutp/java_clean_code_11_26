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
public class ProductService {
    @Autowired
    Store store;

    @RequiresPermission("PRODUCT")
    @Auditable("ADD_PRODUCT")
    @MonitorPerformance(operation = "ADD_PRODUCT", thresholdMs = 300)
    public Product add(Product product) {
        if (product.id() == null || product.id().isBlank() || product.name() == null || product.name().isBlank() || product.price() <= 0 || store.hasProduct(product.id()))
            throw new BusinessException("Invalid product");
        store.addProduct(product);
        return product;
    }

    @RequiresPermission("PRODUCT")
    @Auditable("CHANGE_PRICE")
    @MonitorPerformance(operation = "CHANGE_PRICE", thresholdMs = 300)
    public Product price(String id, int price) {
        Product old = store.product(id);
        if (price <= 0) throw new BusinessException("Invalid price");
        Product product = new Product(id, old.name(), price);
        store.saveProduct(product);
        return product;
    }

    public List<Product> list() {
        return store.products();
    }
}

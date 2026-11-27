package shopflow.repository;

import shopflow.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.*;

import shopflow.model.Models.*;

@Service
public class Store {
    private final Map<String, Product> products = new LinkedHashMap<>();
    private final Map<String, Customer> customers = new LinkedHashMap<>();
    private final Map<String, Order> orders = new LinkedHashMap<>();
    private final Map<String, Integer> stocks = new LinkedHashMap<>();
    private int sequence;

    public Store() {
        reset();
    }

    public void reset() {
        products.clear();
        customers.clear();
        orders.clear();
        stocks.clear();
        sequence = 0;
        products.put("P1", new Product("P1", "Keyboard", 2000));
        customers.put("C1", new Customer("C1", "Alice"));
        stocks.put("P1", 10);
    }

    public Product product(String id) {
        Product result = products.get(id);
        if (result == null) throw new BusinessException("Unknown product");
        return result;
    }

    public Order order(String id) {
        Order result = orders.get(id);
        if (result == null) throw new BusinessException("Unknown order");
        return result;
    }

    public Order save(Order order) {
        orders.put(order.id(), order);
        return order;
    }

    public List<Customer> customers() {
        return List.copyOf(customers.values());
    }

    public List<Order> orders() {
        return List.copyOf(orders.values());
    }

    public List<Product> products() {
        return List.copyOf(products.values());
    }

    public boolean hasCustomer(String id) {
        return customers.containsKey(id);
    }

    public boolean hasProduct(String id) {
        return products.containsKey(id);
    }

    public String nextOrderId() {
        return "O" + (++sequence);
    }

    public int stock(String id) {
        return stocks.get(id);
    }

    public void setStock(String id, int quantity) {
        stocks.put(id, quantity);
    }

    public void increaseStock(String id, int quantity) {
        stocks.merge(id, quantity, Math::addExact);
    }

    public void saveProduct(Product product) {
        products.put(product.id(), product);
    }

    public void addProduct(Product product) {
        saveProduct(product);
        setStock(product.id(), 0);
    }
}

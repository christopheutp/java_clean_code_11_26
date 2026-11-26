package shopflow.controller;

import shopflow.audit.AuditService;
import shopflow.service.CustomerService;
import shopflow.monitoring.MetricsService;
import shopflow.service.OrderService;
import shopflow.payment.PaymentProvider;
import shopflow.service.PaymentService;
import shopflow.service.ProductService;
import shopflow.service.StockService;
import shopflow.repository.Store;
import shopflow.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import shopflow.model.Models.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class Api {
    @Autowired
    UserContext user;
    @Autowired
    OrderService orders;
    @Autowired
    ProductService products;
    @Autowired
    StockService stocks;
    @Autowired
    CustomerService customers;
    @Autowired
    PaymentService payments;
    @Autowired
    PaymentProvider provider;
    @Autowired
    AuditService audit;
    @Autowired
    MetricsService metrics;
    @Autowired
    Store store;

    @PostMapping("/orders")
    public Order create(@RequestHeader(value = "X-User", required = false) String name, @RequestBody CreateOrder input) {
        return user.run(name, () -> orders.create(input));
    }

    @GetMapping("/orders")
    public List<Order> orders(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> orders.list());
    }

    @GetMapping("/orders/{id}")
    public Order order(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id) {
        return user.run(name, () -> orders.get(id));
    }

    @PostMapping("/orders/{id}/confirm")
    public Order confirm(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id) {
        return user.run(name, () -> orders.confirm(id));
    }

    @PostMapping("/orders/{id}/cancel")
    public Order cancel(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id) {
        return user.run(name, () -> orders.cancel(id));
    }

    @PostMapping("/orders/{id}/pay")
    public Order pay(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id) {
        return user.run(name, () -> payments.pay(id));
    }

    @PostMapping("/orders/{id}/refund")
    public Order refund(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id) {
        return user.run(name, () -> payments.refund(id));
    }

    @GetMapping("/products")
    public List<Product> products(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> products.list());
    }

    @PostMapping("/products")
    public Product add(@RequestHeader(value = "X-User", required = false) String name, @RequestBody Product input) {
        return user.run(name, () -> products.add(input));
    }

    @PutMapping("/products/{id}/price/{value}")
    public Product price(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id, @PathVariable("value") int value) {
        return user.run(name, () -> products.price(id, value));
    }

    @GetMapping("/stocks/{id}")
    public Integer stock(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id) {
        return user.run(name, () -> stocks.get(id));
    }

    @PostMapping("/stocks/{id}/restock/{value}")
    public Integer restock(@RequestHeader(value = "X-User", required = false) String name, @PathVariable("id") String id, @PathVariable("value") int value) {
        return user.run(name, () -> stocks.restock(id, value));
    }

    @GetMapping("/customers")
    public List<Customer> customers(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> customers.list());
    }

    @GetMapping("/audit")
    public List<Audit> audit(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> audit.entries());
    }

    @GetMapping("/metrics")
    public List<Metric> metrics(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> metrics.entries());
    }

    @GetMapping("/demo/provider")
    public Map<String, Integer> stats(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> provider.stats());
    }

    @PostMapping("/demo/provider")
    public Map<String, Integer> configure(@RequestHeader(value = "X-User", required = false) String name, @RequestBody PaymentConfig input) {
        return user.run(name, () -> configureProvider(input));
    }

    @PostMapping("/demo/reset")
    public Map<String, String> reset(@RequestHeader(value = "X-User", required = false) String name) {
        return user.run(name, () -> resetAll());
    }

    private Map<String, Integer> configureProvider(PaymentConfig input) {
        provider.configure(input);
        return provider.stats();
    }

    private Map<String, String> resetAll() {
        store.reset();
        provider.reset();
        audit.reset();
        metrics.reset();
        return Map.of("status", "reset");
    }
}

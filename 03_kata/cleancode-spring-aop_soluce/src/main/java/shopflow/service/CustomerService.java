package shopflow.service;

import shopflow.repository.Store;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import shopflow.model.Models.*;

@Service
public class CustomerService {
    @Autowired
    Store store;

    public List<Customer> list() {
        return store.customers();
    }
}

package org.example;

public class Customer {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Customer customer)) {
            return false;
        }
        return java.util.Objects.equals(name, customer.name);
    }
}

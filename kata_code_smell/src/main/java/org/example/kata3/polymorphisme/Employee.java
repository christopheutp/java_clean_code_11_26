package org.example.kata3.polymorphisme;

public abstract class Employee {
    protected int hoursWorked;

    public Employee(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public abstract double calculateSalary();
}

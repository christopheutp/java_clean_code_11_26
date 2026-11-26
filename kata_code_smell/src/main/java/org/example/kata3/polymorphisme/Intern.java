package org.example.kata3.polymorphisme;

public class Intern extends Employee {
    private static final double HOURLY_RATE = 20;

    public Intern(int hoursWorked) {
        super(hoursWorked);
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * HOURLY_RATE;
    }
}

package org.example.kata3.polymorphisme;

public class Manager extends Employee {
    private int teamSize;
    private static final double HOURLY_RATE = 60;
    private static final double BONUS = 2000;

    public Manager(int hoursWorked, int teamSize) {
        super(hoursWorked);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateSalary() {
        double salary = hoursWorked * HOURLY_RATE;
        if (teamSize > 10) {
            salary += BONUS;
        }
        return salary;
    }
}

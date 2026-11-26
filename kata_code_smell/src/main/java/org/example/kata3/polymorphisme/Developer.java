package org.example.kata3.polymorphisme;

public class Developer extends Employee {
    private int yearsOfExperience;
    private static final double HOURLY_RATE = 50;
    private static final double BONUS = 1000;

    public Developer(int hoursWorked, int yearsOfExperience) {
        super(hoursWorked);
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public double calculateSalary() {
        double salary = hoursWorked * HOURLY_RATE;
        if (yearsOfExperience > 5) {
            salary += BONUS;
        }
        return salary;
    }
}

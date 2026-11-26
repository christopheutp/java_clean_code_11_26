package org.example.kata3;

public class Payroll {
    public double calculateSalary(Employee employee) {
        double salary = 0;
        if (employee.getRole().equals("Developer")) {
            salary = employee.getHoursWorked() * 50;
            // Bonus pour les développeurs
            if (employee.getYearsOfExperience() > 5) {
                salary += 1000;
            }
        } else if (employee.getRole().equals("Manager")) {
            salary = employee.getHoursWorked() * 60;
            // Bonus pour les managers
            if (employee.getTeamSize() > 10) {
                salary += 2000;
            }
        } else if (employee.getRole().equals("Intern")) {
            salary = employee.getHoursWorked() * 20;
        }
        // Autres rôles
        return salary;
    }
}

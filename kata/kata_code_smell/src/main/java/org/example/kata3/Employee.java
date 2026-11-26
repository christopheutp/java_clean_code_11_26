package org.example.kata3;

public class Employee {
    private final String role;
    private final int hoursWorked, yearsOfExperience, teamSize;

    public Employee(String role, int hoursWorked, int yearsOfExperience, int teamSize) {
        this.role = role;
        this.hoursWorked = hoursWorked;
        this.yearsOfExperience = yearsOfExperience;
        this.teamSize = teamSize;
    }

    public String getRole() {
        return role;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public int getTeamSize() {
        return teamSize;
    }
}

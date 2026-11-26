package org.example.kata3.polymorphisme;

public class Main {
    public static void main(String[] args) {
        Payroll payroll = new Payroll();
        payroll.calculateSalary(new Developer(150, 10));
        payroll.calculateSalary(new Manager(150, 10));
    }
}

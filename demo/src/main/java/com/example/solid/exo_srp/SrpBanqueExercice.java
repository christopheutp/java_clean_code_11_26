package com.example.solid.exo_srp;

import java.util.ArrayList;
import java.util.List;

public class SrpBanqueExercice {
    static class BankAccount {
        private final List<Integer> transactions = new ArrayList<>();
        void deposit(int amount) { transactions.add(amount); }
        void withdraw(int amount) { transactions.add(-amount); }
        void printStatement() {
            int balance = 0;
            for(int amount: transactions) balance += amount;
            System.out.println("Solde: " + balance); // PROBLEME : le service formate et affiche
        }
    }
    public static void main(String[] args){ BankAccount a=new BankAccount(); a.deposit(20); a.withdraw(5); a.printStatement(); }
}

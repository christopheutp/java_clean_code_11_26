package com.example.app.notifier;


import com.example.app.observer.Observer;

public class EmployeeNotifier implements Observer<String> {

    @Override
    public void update(String message) {
        System.out.println("Notification envoyée : " + message);
    }
}

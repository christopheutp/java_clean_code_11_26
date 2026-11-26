package com.example;

public class PasswordValidator {
    private static final int MINIMUM_LENGTH = 8;

    public boolean isValid(String password) {
        return password.length() >= MINIMUM_LENGTH;
    }
}

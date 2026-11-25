package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setUp() {
        // First : chaque test dispose de sa propre instance
        passwordValidator = new PasswordValidator();
    }

    @Test
    void shouldReturnTrueForPasswordWith8orMoreCharacters() {
        boolean result = passwordValidator.isValid("123456motdepasse");
        assertTrue(result);
    }



}

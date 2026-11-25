package com.example;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatriceTest {


    @Test
    @DisplayName("Given 10 and 20, when adding them, then result is 30")
    void shouldReturnSumOfTwoNumbers() {
       // assertEquals(30,new Calculatrice().add(10,20));
        // Arrange : preparer les donnee
        Calculatrice calculatrice = new Calculatrice();
        // Act : executer une operation metier
        int result = calculatrice.add(10,20);
        // Assert : comparer le resultatt attendu au resultat obtenue
        assertEquals(30,result);
    }
}

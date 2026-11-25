package com.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       // Objet classique
        Temperature a = new Temperature(20);
        Temperature b = new Temperature(20);

        System.out.println("Objet Classique : "+ a.equals(b));


        TemperatureValue c = new TemperatureValue(20);
        TemperatureValue d = new TemperatureValue(20);
        System.out.println("Objet Value : "+ c.equals(d));
        System.out.println("meme instance ? "+ (c == d));



    }
}
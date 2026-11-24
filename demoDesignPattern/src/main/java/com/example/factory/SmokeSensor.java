package com.example.factory;



public class SmokeSensor implements Sensor {
    @Override public String read() { return "Fumée : aucune"; }
}

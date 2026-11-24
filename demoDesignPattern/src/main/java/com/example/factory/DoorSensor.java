package com.example.factory;



public class DoorSensor implements Sensor {
    @Override public String read() { return "Porte : fermée"; }
}

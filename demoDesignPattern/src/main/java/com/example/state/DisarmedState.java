package com.example.state;



public class DisarmedState implements AlarmState {
    @Override public void movement(Alarm alarm) { System.out.println("Mouvement ignoré"); }
    @Override public String name() { return "Désarmée"; }
}

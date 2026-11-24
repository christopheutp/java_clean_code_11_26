package com.example.state;



public class TriggeredState implements AlarmState {
    @Override public void movement(Alarm alarm) { System.out.println("Sirène déjà active"); }
    @Override public String name() { return "Déclenchée"; }
}

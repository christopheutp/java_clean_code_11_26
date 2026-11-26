package com.example.state;



public class ArmedState implements AlarmState {
    @Override public void movement(Alarm alarm) {
        System.out.println("Sirène déclenchée !");
        alarm.setState(new TriggeredState());
    }
    @Override public String name() { return "Armée"; }
}

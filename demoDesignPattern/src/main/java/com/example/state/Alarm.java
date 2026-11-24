package com.example.state;



public class Alarm {
    private AlarmState state = new DisarmedState();
    public void setState(AlarmState state) { this.state = state; }
    public void movement() { state.movement(this); }
    public void status() { System.out.println(state.name()); }
}

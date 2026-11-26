package com.example.visitor;



public class Heater implements Device {
    public int watts() { return 1500; }
    @Override public void accept(DeviceVisitor visitor) { visitor.visit(this); }
}

package com.example.visitor;



public class Lamp implements Device {
    public int watts() { return 10; }
    @Override public void accept(DeviceVisitor visitor) { visitor.visit(this); }
}

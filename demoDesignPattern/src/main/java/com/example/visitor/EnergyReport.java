package com.example.visitor;



public class EnergyReport implements DeviceVisitor {
    private int total;
    @Override public void visit(Lamp lamp) { total += lamp.watts(); }
    @Override public void visit(Heater heater) { total += heater.watts(); }
    public int total() { return total; }
}

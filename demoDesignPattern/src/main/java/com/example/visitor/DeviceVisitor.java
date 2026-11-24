package com.example.visitor;



public interface DeviceVisitor {
    void visit(Lamp lamp);
    void visit(Heater heater);
}

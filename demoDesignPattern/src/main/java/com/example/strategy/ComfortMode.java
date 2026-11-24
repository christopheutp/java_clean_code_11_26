package com.example.strategy;

public class ComfortMode implements TemperaturePolicy {
    @Override
    public int targetTemperature() {
        return 22;
    }
}

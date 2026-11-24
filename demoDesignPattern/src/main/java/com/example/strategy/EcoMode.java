package com.example.strategy;

public class EcoMode implements TemperaturePolicy {
    @Override
    public int targetTemperature() {
        return 18;
    }
}

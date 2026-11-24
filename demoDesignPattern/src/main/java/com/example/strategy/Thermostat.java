package com.example.strategy;

public class Thermostat {
    private TemperaturePolicy policy;

    public Thermostat(TemperaturePolicy policy) {
        this.policy = policy;
    }

    public void setPolicy(TemperaturePolicy policy) {
        this.policy = policy;
    }

    public void regulate() {
        System.out.println("Consigne : "+ policy.targetTemperature()+" °c");
    }
}

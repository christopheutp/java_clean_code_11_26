package com.example.observer;



import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor {
    private final List<TemperatureListener> listeners = new ArrayList<>();
    public void subscribe(TemperatureListener listener) { listeners.add(listener); }
    public void measure(int degrees) {
        for (TemperatureListener listener : listeners) listener.onTemperature(degrees);
    }
}

package com.example.factory;





public class SensorFactory {
    private SensorFactory() {}
    public static Sensor create(String type) {
        return switch (type) {
            case "door" -> new DoorSensor();
            case "smoke" -> new SmokeSensor();
            default -> throw new IllegalArgumentException("Capteur inconnu : " + type);
        };
    }
}

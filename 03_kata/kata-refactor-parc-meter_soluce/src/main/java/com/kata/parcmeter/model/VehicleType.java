package com.kata.parcmeter.model;


public enum VehicleType {
    CAR(3.00), MOTORBIKE(2.00), TRUCK(6.00);

    private final double hourlyRate;

    VehicleType(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Price getHourlyRate() {
        return Price.of(hourlyRate);
    }

    public static VehicleType fromString(String type) {
        if (type == null) return CAR;
        try {
            return VehicleType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return CAR;
        }
    }
}

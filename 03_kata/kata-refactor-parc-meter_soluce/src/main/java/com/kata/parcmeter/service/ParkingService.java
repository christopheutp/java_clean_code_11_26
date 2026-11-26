package com.kata.parcmeter.service;

import com.kata.parcmeter.discount.PromoCode;
import com.kata.parcmeter.model.ParkingSession;
import com.kata.parcmeter.model.Session;
import com.kata.parcmeter.model.VehicleType;

public class ParkingService {

    public double calculate(Session legacySession, boolean applyPromo) {
        if (legacySession == null || legacySession.in == null || legacySession.out == null || legacySession.out.isBefore(legacySession.in)) {
            return 0.0;
        }
        ParkingSession session = toParkingSession(legacySession);
        return session.calculateCost(applyPromo).asDouble();
    }

    private ParkingSession toParkingSession(Session input) {
        VehicleType type = VehicleType.fromString(input.vehicleType);
        PromoCode promo = input.promo != null ? PromoCode.of(input.promo) : PromoCode.none();

        return new ParkingSession(type, input.in, input.out, promo, input.wknd);
    }
}

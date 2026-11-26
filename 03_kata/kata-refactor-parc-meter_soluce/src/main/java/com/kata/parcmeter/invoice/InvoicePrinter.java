package com.kata.parcmeter.invoice;

import com.kata.parcmeter.model.Session;
import com.kata.parcmeter.service.ParkingService;

public class InvoicePrinter {
    public String print(Session session, ParkingService service) {
        double price = getPrice(session, service);
        String weekendStatus = session.wknd ? "WEEKEND" : "WEEKDAY";
        String promoStatus = session.promo != null ? session.promo : "NO_PROMO";
        return String.format("INVOICE[%s;%s;%s]=%s€", session.vehicleType, weekendStatus, promoStatus, price);
    }

    public double getPrice(Session session, ParkingService service) {
        return service.calculate(session, true);
    }
}

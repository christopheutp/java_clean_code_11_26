package org.example.kata6;

import java.util.EnumMap;
import java.util.Map;

public class BookingSystem {
    private final Map<EventType, Runnable> bookings = new EnumMap<>(EventType.class);

    public BookingSystem() {
        bookings.put(EventType.CONFERENCE, this::bookConference);
        bookings.put(EventType.MEETING, this::bookMeeting);
        bookings.put(EventType.WEDDING, this::bookWedding);
    }

    public void bookRoom(Event event) {
        Runnable booking = bookings.get(event.getType());
        if (booking != null) booking.run();
    }

    private void bookConference() {
        // Réserver une salle de conférence, configurer l’équipement audiovisuel et organiser la restauration.
    }

    private void bookMeeting() {
        // Réserver une salle de réunion et organiser les boissons.
    }

    private void bookWedding() {
        // Réserver une salle de banquet, organiser la décoration, le service traiteur et la musique.
    }
}

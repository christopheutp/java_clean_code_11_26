package org.example.kata6;

public class BookingSystem {
    public void bookRoom(Event event) {
        if (event.getType() == EventType.CONFERENCE) {
            // Réserver une salle de conférence
            // Configurer l'équipement audiovisuel
            // Organiser le service de restauration
        } else if (event.getType() == EventType.MEETING) {
            // Réserver une salle de réunion
            // Organiser les boissons
        } else if (event.getType() == EventType.WEDDING) {
            // Réserver une salle de banquet
            // Décoration
            // Service traiteur
            // Musique
        }
        // Autres types d'événements
    }
}

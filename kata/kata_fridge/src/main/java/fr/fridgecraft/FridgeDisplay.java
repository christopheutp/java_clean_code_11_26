package fr.fridgecraft;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

final class FridgeDisplay {

    // Exigence 4 : articles expirés en premier, puis articles non expirés.
    static String format(List<FoodItem> items, LocalDateTime now) {
        List<String> expired = new ArrayList<>();
        List<String> remaining = new ArrayList<>();
        for (FoodItem item : items) {
            if (item.isExpiredAt(now)) {
                expired.add("EXPIRÉ : " + item.name());
            } else {
                long days = Duration.between(now, item.expirationDate()).toDays();
                remaining.add(item.name() + " : " + days + " jour(s) restant(s)");
            }
        }
        expired.addAll(remaining);
        return String.join("\n", expired);
    }
}

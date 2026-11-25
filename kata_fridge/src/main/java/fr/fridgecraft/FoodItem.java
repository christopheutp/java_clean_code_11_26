package fr.fridgecraft;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record FoodItem(UUID id, String name, LocalDateTime expirationDate,
                       LocalDateTime addedAt, PackagingState packagingState) {
    public FoodItem {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(expirationDate);
        Objects.requireNonNull(addedAt);
        Objects.requireNonNull(packagingState);
        if (name.isBlank() || name.contains("\n") || name.contains("\r")) {
            throw new IllegalArgumentException("Nom d'article invalide");
        }
    }

    // Exigence 3 : la date de péremption se dégrade de 1 h (scellé) ou 5 h (ouvert).
    FoodItem afterFridgeOpening() {
        long hours = packagingState == PackagingState.SEALED ? 1 : 5;
        return new FoodItem(id,name,expirationDate.minusHours(hours), addedAt, packagingState);
    }

    boolean isExpiredAt(LocalDateTime now) {
        return !expirationDate.isAfter(now);
    }
}

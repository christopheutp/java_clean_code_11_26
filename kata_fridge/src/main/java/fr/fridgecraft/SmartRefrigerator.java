package fr.fridgecraft;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class SmartRefrigerator {
    private final List<FoodItem> items = new ArrayList<>();
    private final Clock clock;

    public SmartRefrigerator() {

        this(Clock.systemDefaultZone());
    }

    // Une horloge injectable permet de tester les dates sans dépendre de l'heure réelle
    public SmartRefrigerator(Clock clock) {

        this.clock = Objects.requireNonNull(clock);
    }

    // Exigence 1 : suivre les articles placés dans le réfrigérateur.
    // Exigence 2 : mémoriser nom, expiration et horodatage automatique de l'ajout.
    public UUID addItem(String name, LocalDateTime expirationDate, PackagingState state) {
        UUID id = UUID.randomUUID();
        items.add(new FoodItem(id, name, expirationDate, LocalDateTime.now(clock), state));
        return id;
    }

    // Exigence 1 : suivre les articles sortis en les retirant du contenu courant.
    public boolean removeItem(UUID id) {
        return items.removeIf(item -> item.id().equals(id));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<FoodItem> getItems() {
        return List.copyOf(items);
    }

    // Exigence 3 : appliquer la dégradation à chaque article déjà présent.
    public void open() {
        items.replaceAll(FoodItem::afterFridgeOpening);
    }

    // Exigence 4 : fournir un affichage formaté du contenu et du temps restant.
    public String displayContents() {
        return FridgeDisplay.format(items, LocalDateTime.now(clock));
    }
}

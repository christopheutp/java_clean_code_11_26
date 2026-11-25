package org.example.rpg.model;

import java.util.List;
/** Données de la demande de combat : aucune logique de calcul ici. */
public record GameContext(String playerClass, int playerLevel, String actionType,
                          String targetType, int targetLevel, String environment,
                          List<String> playerItems, String weather) {
    public GameContext { playerItems = List.copyOf(playerItems); }
    public boolean has(String item) { return playerItems.contains(item); }
}

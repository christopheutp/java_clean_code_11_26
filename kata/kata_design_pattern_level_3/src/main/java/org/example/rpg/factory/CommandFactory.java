package org.example.rpg.factory;

import org.example.rpg.command.*;
import org.example.rpg.model.*;
import org.example.rpg.strategy.PlayerStrategy;
import java.util.function.DoubleSupplier;
public final class CommandFactory {
    public GameCommand create(GameContext c, PlayerStrategy s, DoubleSupplier random, String itemToUse) {
        return switch(c.actionType()) {
            case "ATTACK" -> new AttackCommand(c,s,random);
            case "CAST_SPELL" -> new CastSpellCommand(c,s,random);
            case "USE_ITEM" -> new UseItemCommand(c,itemToUse);
            default -> throw new IllegalArgumentException("Unknown action " + c.actionType());
        };
    }
}

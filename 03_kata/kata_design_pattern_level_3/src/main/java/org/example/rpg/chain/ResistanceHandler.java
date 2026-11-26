package org.example.rpg.chain;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
/** Étape 3 : résistances des ennemis, même pour CAST_SPELL (sujet lignes 173-187). */
public final class ResistanceHandler extends DamageHandler {
    protected void change(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random) {
        switch(c.targetType()) {
            case "DRAGON" -> { r.multiply(.7); r.log("Dragon scales reduce damage"); }
            case "SKELETON" -> { if("MAGE".equals(c.playerClass())) { r.multiply(.5); r.log("Skeleton resists magic"); } }
            case "GHOST" -> { if(!"MAGE".equals(c.playerClass())) { r.multiply(.3); r.log("Physical attack passes through ghost"); } }
            default -> { }
        }
    }
}

package org.example.rpg.visitor;

import org.example.rpg.model.*;

public final class EnvironmentVisitor implements ActionVisitor {
    public void visitAttack(GameContext c, CombatResult r) {
        switch (c.environment()) {
            case "SWAMP" -> { r.multiply(.8); r.log("Swamp terrain: -20% damage"); }
            case "MOUNTAIN" -> { r.multiply(1.1); r.log("High ground: +10% damage"); }
            case "DESERT" -> { if ("SANDSTORM".equals(c.weather())) { r.multiply(.6); r.log("Sandstorm: -40% damage"); } }
            default -> { }
        }
    }
    public void visitSpell(GameContext c, CombatResult r) { /* Pas de modificateur général dans le sujet original. */ }
    public void visitItem(GameContext c, CombatResult r) { /* Extension sans changer les actions. */ }
}

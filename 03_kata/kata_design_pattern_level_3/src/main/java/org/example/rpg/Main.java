package org.example.rpg;

import org.example.rpg.model.*;
import org.example.rpg.observer.*;
import org.example.rpg.composite.*;
import org.example.rpg.spell.*;
import org.example.rpg.memento.*;
import java.util.List;
/** Demo : les 3 niveaux sans dependance externe. */
public final class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine(() -> .1); // RNG déterministe pour la démo
        engine.addObserver(new ConsoleCombatObserver());
        System.out.println("Niveaux 1+2 : Strategy, Factory, Command, Observer, Chain, State");
        engine.executePlayerAction("MAGE",15,"CAST_SPELL","DRAGON",20,"MOUNTAIN",List.of("FIREBALL_SCROLL"),"STORM");
        GameContext rogue = new GameContext("ROGUE",12,"ATTACK","GHOST",8,"DUNGEON",List.of("SHADOW_BLADES"),"CLEAR");
        CombatResult stealth = engine.play(rogue,"STEALTH",null);
        System.out.println("Rogue stealth damage = " + stealth.damage());
        System.out.println("Niveau 3 : Composite");
        CombinedSpell combo = new CombinedSpell(List.of(new AtomicSpell(new FireballSpell()), new AtomicSpell(new HealSpell())));
        CombatResult combinedResult = new CombatResult(); combo.cast(new GameContext("MAGE",4,"CAST_SPELL","DRAGON",4,"FOREST",List.of(),"CLEAR"),combinedResult);
        System.out.println("Combined damage = " + combinedResult.damage()+", health = "+combinedResult.health());
        System.out.println("Niveau 3 : Memento");
        CombatSession session = new CombatSession(); CombatHistory history = new CombatHistory();
        history.checkpoint(session); session.setState("POISONED"); session.change(-15,-10,50,"turn 1");
        history.undo(session);
        System.out.println("Restored HP = "+session.health()+", state = "+session.state());
    }
}

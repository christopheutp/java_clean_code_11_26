package org.example.rpg.command;

import org.example.rpg.factory.ItemFactory;
import org.example.rpg.model.*;
/** Nouveau type d'action que le moteur original ne prenait pas en charge. */
public final class UseItemCommand implements GameCommand {
    private final GameContext context;
    private final String item;
    public UseItemCommand(GameContext context,String item) { this.context=context; this.item=item; }
    public CombatResult execute() {
        CombatResult r = new CombatResult();
        if(!context.has(item)) { r.cancel("Missing item: " + item); return r; }
        new ItemFactory().create(item).use(context,r);
        return r;
    }
    public CombatAction action() { return new ItemAction(); }
}

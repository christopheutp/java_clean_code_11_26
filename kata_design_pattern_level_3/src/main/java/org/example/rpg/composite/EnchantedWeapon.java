package org.example.rpg.composite;

import org.example.rpg.model.*;
/** Composite d'effets d'arme : éléments assemblables plutôt qu'une condition supplémentaire. */
public final class EnchantedWeapon {
    private final String name;
    private final int damageBonus;
    public EnchantedWeapon(String name, int bonus) { this.name=name; damageBonus=bonus; }
    public void apply(CombatResult r) { r.damage(r.damage()+damageBonus); r.log(name+" bonus +"+damageBonus); }
}

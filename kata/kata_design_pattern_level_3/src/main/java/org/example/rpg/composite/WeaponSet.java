package org.example.rpg.composite;

import org.example.rpg.model.*;
import java.util.List;
/** Composite : un ensemble d'enchantements se manipule comme une seule source de bonus. */
public final class WeaponSet {
    private final List<EnchantedWeapon> enchantments;
    public WeaponSet(List<EnchantedWeapon> enchantments) { this.enchantments=List.copyOf(enchantments); }
    public void apply(CombatResult result) { for(EnchantedWeapon e:enchantments) e.apply(result); }
}

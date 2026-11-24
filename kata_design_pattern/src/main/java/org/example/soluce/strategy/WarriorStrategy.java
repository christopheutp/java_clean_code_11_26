package org.example.soluce.strategy;

import org.example.soluce.CombatResult;
import org.example.soluce.GameContext;

public class WarriorStrategy implements PlayerClassStrategy {
    @Override
    public CombatResult executeAction(String actionType, GameContext context) {
        CombatResult result = new CombatResult();

        int damage = 30 + (context.getPlayerLevel() * 4);
        result.setManaChange(-25);

        if("ATTACK".equals(actionType)) {


            // Effets des armes
            if (context.getPlayerItems().contains("LEGENDARY_SWORD")) {
                damage += 25;
                System.out.println("⚔️ Legendary sword glows with power!");
            } else if (context.getPlayerItems().contains("MAGIC_AXE")) {
                damage += 15;
                if (Math.random() > 0.7) {
                    damage *= 1.5;
                    System.out.println("⚡ Magic axe unleashes lightning!");
                }
            } else if (context.getPlayerItems().contains("RUSTY_BLADE")) {
                damage += 5;
                if (Math.random() > 0.9) {
                    System.out.println("💥 Rusty blade breaks apart!");
                    damage = 1;
                }
            }

            // Effets critique
            if (Math.random() > 0.8) {
                damage *= 2;
                result.setCriticalHit(true);
                System.out.println("💥 CRITICAL HIT!");
            }

        }else if("CAST_SPELL".equals(actionType)) {
            if (context.getPlayerItems().contains("RAGE_POTION")) {
                damage = (50 + (damage * 3)) * 2; // Double dégâts
                result.setHealthChange(result.getHealthChange() -= 10); // Coût en vie
                System.out.println("😡 BERSERKER RAGE!");

            } else {
                System.out.println("Warriors cannot cast spells without potions!");
                return;
            }
        }

        return result;
    }
}

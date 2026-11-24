package org.example.soluce.strategy;

import org.example.soluce.CombatResult;
import org.example.soluce.GameContext;

public class RogueStrategy implements PlayerClassStrategy{
    @Override
    public CombatResult executeAction(String actionType, GameContext context) {

        if("ATTACK".equals(actionType)) {

            damage = 35 + (playerLevel * 2);

            // Attaque furtive
            if (Math.random() > 0.6) {
                damage *= 1.8;
                System.out.println("🗡️ Sneak attack from shadows!");
            }

            // Effets des dagues
            if (playerItems.contains("POISON_DAGGERS")) {
                damage += 12;
                System.out.println("☠️ Poison courses through enemy veins!");
            } else if (playerItems.contains("SHADOW_BLADES")) {
                damage += 18;
                if (environment.equals("CAVE") || environment.equals("DUNGEON")) {
                    damage += 10;
                    System.out.println("🌑 Darkness empowers shadow blades!");
                }
            }

        }else if("CAST_SPELL".equals(actionType)) {
            if (playerItems.contains("SMOKE_BOMB")) {
                System.out.println("💨 Smoke bomb creates diversion!");
                if (Math.random() > 0.5) {
                    damage = (35 + (playerLevel * 2)) * 2; // Attaque surprise
                    System.out.println("🗡️ Surprise attack through smoke!");
                } else {
                    damage = 0;
                    System.out.println("💨 Attack missed in the smoke!");
                }
            } else {
                System.out.println("Rogues need special items to use magic!");
                return;
            }
        }

        return null;
    }
}

package org.example.soluce.strategy;

import org.example.soluce.CombatResult;
import org.example.soluce.GameContext;

public class MageStrategy implements PlayerClassStrategy {
    @Override
    public CombatResult executeAction(String actionType, GameContext context) {

        if("ATTACK".equals(actionType)) {
            damage = 30 + (playerLevel * 4);
            mana -= 25;

            // Effets des bâtons magiques
            if (playerItems.contains("STAFF_OF_FIRE")) {
                damage += 20;
                System.out.println("🔥 Flames engulf the enemy!");
                if (environment.equals("FOREST")) {
                    damage += 10; // Bonus feu en forêt
                    System.out.println("🔥 Forest catches fire - extra damage!");
                }
            } else if (playerItems.contains("ICE_WAND")) {
                damage += 15;
                System.out.println("❄️ Ice crystals form around target!");
                if (weather.equals("SNOW")) {
                    damage += 8;
                    System.out.println("❄️ Blizzard amplifies ice magic!");
                }
            }

            if (mana < 0) {
                System.out.println("💙 Not enough mana!");
                return;
            }
        }else if("CAST_SPELL".equals(actionType)) {

            if (playerItems.contains("FIREBALL_SCROLL")) {
                damage = 40 + (playerLevel * 5);
                mana -= 30;
                System.out.println("🔥 FIREBALL!");

                if (targetType.equals("PLANT_MONSTER") || targetType.equals("TREE_SPIRIT")) {
                    damage *= 2;
                    System.out.println("🔥 Fire is super effective against plant creatures!");
                }

            } else if (playerItems.contains("LIGHTNING_SCROLL")) {
                damage = 45 + (playerLevel * 4);
                mana -= 35;
                System.out.println("⚡ LIGHTNING BOLT!");

                if (weather.equals("STORM")) {
                    damage *= 1.5;
                    System.out.println("⛈️ Storm amplifies lightning magic!");
                }

                if (targetType.equals("WATER_ELEMENTAL") || targetType.equals("METAL_GOLEM")) {
                    damage *= 1.8;
                    System.out.println("⚡ Lightning is super effective!");
                }

            } else if (playerItems.contains("HEAL_SCROLL")) {
                health += 50 + (playerLevel * 2);
                mana -= 20;
                System.out.println("✨ HEAL!");
                damage = 0; // Pas de dégâts pour un sort de soin
            }
        }

        return null;
    }
}

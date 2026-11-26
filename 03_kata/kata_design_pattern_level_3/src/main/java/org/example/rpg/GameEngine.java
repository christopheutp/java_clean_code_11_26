package org.example.rpg;

import org.example.rpg.chain.DamagePipeline;
import org.example.rpg.command.GameCommand;
import org.example.rpg.factory.*;
import org.example.rpg.model.*;
import org.example.rpg.observer.*;
import org.example.rpg.state.*;
import org.example.rpg.memento.CombatSession;
import java.util.List;
import java.util.function.DoubleSupplier;
/** Moteur : orchestre sans connaître les calculs concrets.
 * N1 = Strategy/Factory/Command ; N2 = Observer/Chain/State ; N3 = Visitor/Composite/Memento.
 */
public final class GameEngine {
    private final PlayerStrategyFactory players = new PlayerStrategyFactory();
    private final CommandFactory commands = new CommandFactory();
    private final DamagePipeline pipeline = new DamagePipeline();
    private final CombatEventBus events = new CombatEventBus();
    private final DoubleSupplier random;
    public GameEngine() {this(Math::random);}
    public GameEngine(DoubleSupplier random) {this.random = random;}
    public void addObserver(CombatObserver observer) {events.subscribe(observer);}
    public CombatResult play(GameContext ctx) {return play(ctx,"NORMAL",null);}
    /** item utilisé seulement pour USE_ITEM ; état NORMAL préserve les règles d'origine. */
    public CombatResult play(GameContext ctx, String stateName, String itemToUse) {
        GameCommand command=commands.create(ctx,players.create(ctx.playerClass()),random,itemToUse);
        CombatResult result=command.execute();
        if(result.cancelled()) return result;
        // Les modificateurs de classe/arme/critique précèdent la chaîne de dégâts.
        pipeline.apply(ctx,command.action(),result,random);
        new StateFactory().create(stateName).beforeAction(result);
        if(result.critical()) events.publish("CRITICAL","Critical attack by " + ctx.playerClass());
        if(result.damage()>100) events.publish("DEVASTATING","Bonus XP gained");
        if(result.critical() && "ROGUE".equals(ctx.playerClass())) events.publish("STEALTH","Critical rogue bonus");
        if(result.mana()<20) events.publish("LOW_MANA","Consider mana potion");
        return result;
    }
    /** Tour persistant : le Memento peut sauvegarder CombatSession avant cet appel et la restaurer. */
    public CombatResult playTurn(GameContext ctx, CombatSession session, String itemToUse) {
        CombatResult r = play(ctx,session.state(),itemToUse);
        if(!r.cancelled()) {
            int xp = r.damage() > 100 ? 10 : 0;
            session.change(r.health()-200,r.mana()-100,xp,ctx.actionType());
            if (xp > 0) events.publish("XP_GAINED", "Experience +"+xp);
        }
        return r;
    }
    /** API de compatibilité avec le sujet. */
    public void executePlayerAction(String playerClass,int playerLevel,String actionType,
         String targetType,int targetLevel,String environment,List<String> items,String weather) {
        GameContext ctx=new GameContext(playerClass,playerLevel,actionType,targetType,targetLevel,environment,items,weather);
        CombatResult r=play(ctx);
        for(String msg : r.messages()) System.out.println(msg);
        if(r.cancelled()) return;
        System.out.printf("%n=== COMBAT RESULT ===%nPlayer: %s (Level %d)%nTarget: %s (Level %d)%n"+
             "Environment: %s (%s)%nAction: %s%nDamage dealt: %d%nCritical hit: %b%nPlayer health: %d%nPlayer mana: %d%n",
             playerClass,playerLevel,targetType,targetLevel,environment,weather,actionType,r.damage(),r.critical(),r.health(),r.mana());
        if(r.damage()>100) System.out.println("DEVASTATING BLOW! Bonus XP gained!");
        if(r.critical() && "ROGUE".equals(playerClass)) System.out.println("Critical rogue stealth bonus!");
        if(r.mana()<20) System.out.println("Low mana warning!");
        System.out.println("Combat round completed!");
    }
}

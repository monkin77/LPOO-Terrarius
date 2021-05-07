package Controller;

import GUI.GUI;
import Model.arena.Arena;

import java.util.List;

public class ArenaController {
    private final HeroController heroController;
    private final EnemyController enemyController;
    private final int enemyMovesPerAction;
    private final int gravityUpdatesPerAction;
    private final int inputUpdatesPerAction;
    private int updateCounter;

    public ArenaController(Arena arena, int timePerUpdate) {
        this.heroController = new HeroController(arena);
        this.enemyController = new EnemyController(arena);
        this.enemyMovesPerAction = Math.max(128 / timePerUpdate, 1);
        this.gravityUpdatesPerAction = Math.max(16 / timePerUpdate, 1);
        this.inputUpdatesPerAction = Math.max(16 / timePerUpdate, 1);
        this.updateCounter = 0;
    }

    public void doAction(GUI.ACTION action) {
        heroController.doAction(action);
    }

    public void processInputs(){

    }

    public void timedActions(List<GUI.ACTION> actionList) {
        updateCounter++;
        if (updateCounter % enemyMovesPerAction == 0) {
            enemyController.moveEnemies();
        }
        if (updateCounter % gravityUpdatesPerAction == 0){
            heroController.fallHero();
        }
        if (updateCounter % inputUpdatesPerAction == 0) {
            for (GUI.ACTION action : actionList) {
                this.doAction(action);
            }
        }
        updateCounter = updateCounter % Math.max(gravityUpdatesPerAction, enemyMovesPerAction);
    }

    public boolean checkEnd() {
        return heroController.isHeroAlive();
    }
}

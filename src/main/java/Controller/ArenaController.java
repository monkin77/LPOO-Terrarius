package Controller;

import GUI.GUI;
import Model.arena.Arena;

public class ArenaController {
    private final HeroController heroController;
    private final EnemyController enemyController;
    private final int updatesPerAction;
    private int updateCounter;

    public ArenaController(Arena arena, int timePerUpdate) {
        this.heroController = new HeroController(arena);
        this.enemyController = new EnemyController(arena);
        this.updatesPerAction = Math.max(128 / timePerUpdate, 1);
        this.updateCounter = 0;
    }

    public void doAction(GUI.ACTION action) {
        heroController.doAction(action);
    }

    public void timedActions() {
        updateCounter++;
        if (updateCounter % updatesPerAction == 0) {
            enemyController.moveEnemies();
            updateCounter = 0;
        }
    }

    public boolean checkEnd() {
        return heroController.isHeroAlive();
    }
}

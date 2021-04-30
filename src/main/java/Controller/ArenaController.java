package Controller;

import GUI.GUI;
import Model.arena.Arena;

public class ArenaController {
    private final HeroController heroController;
    private final EnemyController enemyController;

    public ArenaController(Arena arena) {
        this.heroController = new HeroController(arena);
        this.enemyController = new EnemyController(arena);
    }

    // All these methods into Middleman smell. Change if it doesn't get more useful?
    public void doAction(GUI.ACTION action) {
        heroController.doAction(action);
    }

    public void timedActions() {
        enemyController.moveEnemies();
    }

    public boolean checkEnd() {
        return heroController.isHeroAlive();
    }
}

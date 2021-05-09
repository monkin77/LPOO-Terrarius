package Controller;

import GUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class ArenaController {
    private final int updatesPerEnemyAction;
    private final int updatesPerGravityAction;
    private final int updatesPerInputAction;
    private final int maxCounter;

    private final HeroController heroController;
    private final EnemyController enemyController;

    private int updateCounter;
    private List<GUI.ACTION> actionList;

    public ArenaController(HeroController heroController, EnemyController enemyController, int timePerUpdate) {
        this.heroController = heroController;
        this.enemyController = enemyController;
        this.actionList = new ArrayList<>();

        this.updatesPerEnemyAction = Math.max(128 / timePerUpdate, 1);
        this.updatesPerGravityAction = Math.max(16 / timePerUpdate, 1);
        this.updatesPerInputAction = Math.max(16 / timePerUpdate, 1);

        this.maxCounter = updatesPerEnemyAction * updatesPerGravityAction * updatesPerInputAction;
        this.updateCounter = 0;
    }

    public void addActions(List<GUI.ACTION> newActions){
        for (GUI.ACTION action : newActions)
            if (!actionList.contains(action)) actionList.add(action);
    }

    public void update() {
        updateCounter++;
        if (updateCounter % updatesPerEnemyAction == 0)
            enemyController.moveEnemies();

        if (updateCounter % updatesPerGravityAction == 0){
            heroController.fallHero();
            enemyController.fallEnemies();
        }

        if (updateCounter % updatesPerInputAction == 0) {
            for (GUI.ACTION action : actionList) {
                this.heroController.doAction(action);
            }
            actionList.clear();
        }

        updateCounter %= maxCounter;
    }

    public boolean checkEnd() {
        return !heroController.isHeroAlive();
    }
}

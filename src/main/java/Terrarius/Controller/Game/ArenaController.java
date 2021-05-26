package Terrarius.Controller.Game;

import Terrarius.GUI.GUI;
import Terrarius.Model.Game.Position;

import java.util.ArrayList;
import java.util.List;

public class ArenaController {
    private final int updatesPerEnemyMovement;
    private final int updatesPerGravityAction;
    private final int updatesPerInputAction;
    private final int updatesPerEnemyDamage;
    private final int maxCounter;
    private final int timePerUpdate;

    private final HeroController heroController;
    private final EnemyController enemyController;

    private int updateCounter;
    private List<GUI.ACTION> actionList;


    public ArenaController(HeroController heroController, EnemyController enemyController, int timePerUpdate) {
        this.heroController = heroController;
        this.enemyController = enemyController;
        this.timePerUpdate = timePerUpdate;
        this.actionList = new ArrayList<>();

        this.updatesPerEnemyMovement = Math.max(128 / timePerUpdate, 1);
        this.updatesPerGravityAction = Math.max(16 / timePerUpdate, 1);
        this.updatesPerInputAction = Math.max(16 / timePerUpdate, 1);
        this.updatesPerEnemyDamage = Math.max(800 / timePerUpdate, 1);

        this.maxCounter = updatesPerEnemyMovement * updatesPerGravityAction * updatesPerInputAction * updatesPerEnemyDamage;
        this.updateCounter = 0;
    }

    public void addActions(List<GUI.ACTION> newActions) {
        actionList = newActions;
    }

    public void setHeroTargetPosition(Position position){
        this.heroController.setTargetPosition(position);
    }

    public void update() {
        updateCounter++;
        if (updateCounter % updatesPerEnemyMovement == 0)
            enemyController.moveEnemies();

        if (updateCounter % updatesPerGravityAction == 0){
            heroController.fallHero();
            enemyController.fallEnemies();
        }

        if (updateCounter % updatesPerInputAction == 0) {
            for (GUI.ACTION action : actionList) {
                this.heroController.doAction(action);
            }
<<<<<<< HEAD:src/main/java/Controller/ArenaController.java
            actionList.clear();

=======
>>>>>>> master:src/main/java/Terrarius/Controller/Game/ArenaController.java
        }

        if (updateCounter % updatesPerEnemyDamage == 0)
            enemyController.damageHero();

        heroController.updateBuffs(timePerUpdate);
        updateCounter %= maxCounter;
    }

    public boolean checkEnd() {
        return !heroController.isHeroAlive();
    }
}

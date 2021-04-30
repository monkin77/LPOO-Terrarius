package Controller;

import Model.Position;
import Model.arena.Arena;
import Model.elements.enemies.Enemy;

public class EnemyController {
    private final Arena arena;

    public EnemyController(Arena arena) {
        this.arena = arena;
    }

    public void moveEnemies() {
        for (Enemy enemy : arena.getEnemies()) {
            if (enemy.getPosition().getX() < arena.getHero().getPosition().getX())
                moveEnemy(enemy, enemy.getPosition().getRight());
            else
                moveEnemy(enemy, enemy.getPosition().getLeft());
        }
    }

    private void moveEnemy(Enemy enemy, Position position) {
        if (arena.isEmpty(position)) {
            enemy.setPosition(position);
            // TO DO: TAKE INTO ACCOUNT WIDTH AND HEIGHT
            if (arena.getHero().getPosition().equals(position))
                arena.getHero().setHealth(arena.getHero().getHealth() - enemy.getStats().getPower());
        }
    }
}

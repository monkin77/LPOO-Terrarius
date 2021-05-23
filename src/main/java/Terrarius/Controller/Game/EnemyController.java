package Terrarius.Controller.Game;

import Terrarius.Model.Position;
import Terrarius.Model.arena.Arena;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.enemies.Enemy;

public class EnemyController {
    private final Arena arena;

    public EnemyController(Arena arena) {
        this.arena = arena;
    }

    // TODO: Make them only follow the hero when close
    public void moveEnemies() {
        for (Enemy enemy : arena.getEnemies()) {
            if (enemy.getPosition().getX() < arena.getHero().getPosition().getX()) {
                moveEnemy(enemy, enemy.getPosition().getRight());
                enemy.setOrientation(Element.Orientation.RIGHT);
            }
            else {
                moveEnemy(enemy, enemy.getPosition().getLeft());
                enemy.setOrientation(Element.Orientation.LEFT);
            }
        }
    }

    public void fallEnemies(){
        for (Enemy enemy : arena.getEnemies()) {
            moveEnemy(enemy, enemy.getPosition().getDown());
        }
    }

    private void moveEnemy(Enemy enemy, Position position) {
        if(position.getX() < 0) return;
        if ( (!arena.collides(position, enemy.getDimensions())) && (!hasEnemy(enemy, position)) ) {
            enemy.setPosition(position);
            // TODO: USE COLLISIONS
            if (arena.getHero().getPosition().equals(position))
                arena.getHero().setHealth(arena.getHero().getHealth() - enemy.getStats().getPower());
        }
    }

    private boolean hasEnemy(Enemy enemy, Position position) {
        for(Enemy currEnemy : arena.getEnemies()) {
            if (currEnemy.equals(enemy)) continue;
            if (arena.checkElementsCollision(position, enemy.getDimensions(), currEnemy.getPosition(), currEnemy.getDimensions()))
                return true;
        }
        return false;
    }
}

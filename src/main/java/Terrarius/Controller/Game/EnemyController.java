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
        if ( (!arena.collides(position, enemy.getDimensions())) && (!hasEnemy(enemy, position)) ) {
            enemy.setPosition(position);
            // TODO: USE COLLISIONS
            if (arena.getHero().getPosition().equals(position))
                arena.getHero().setHealth(arena.getHero().getHealth() - enemy.getStats().getPower());
        }
    }

    private boolean hasEnemy(Enemy enemy, Position position) {
        for(Enemy currEnemy : arena.getEnemies()) {
            Position currEnemyPos = currEnemy.getPosition();
            if(currEnemy.equals(enemy)) continue;

            boolean leftSideEnemyCollides = position.getX() >= currEnemyPos.getX() &&
                    position.getX() <= currEnemyPos.getX() + currEnemy.getDimensions().getWidth() - 1;

            boolean rightSideEnemyCollides = position.getX() <= currEnemyPos.getX() &&
                    position.getX() + enemy.getDimensions().getWidth() - 1 >= currEnemyPos.getX();

            if(leftSideEnemyCollides || rightSideEnemyCollides) {
                boolean topEnemyCollides = position.getY() >= currEnemyPos.getY() &&
                        position.getY() <= currEnemyPos.getY() + currEnemy.getDimensions().getHeight() - 1;

                boolean bottomEnemyCollides = position.getY() <= currEnemyPos.getY() &&
                        position.getY() + enemy.getDimensions().getHeight() - 1 >= currEnemyPos.getY();

                if(topEnemyCollides || bottomEnemyCollides) return true;
            }
        }
        return false;
    }
}

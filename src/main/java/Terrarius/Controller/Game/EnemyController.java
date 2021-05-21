package Terrarius.Controller.Game;

import Terrarius.Model.Position;
import Terrarius.Model.arena.Arena;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.elements.enemies.Enemy;
import Terrarius.Viewer.Game.GameViewerConstants;

public class EnemyController {
    private final Arena arena;

    public EnemyController(Arena arena) {
        this.arena = arena;
    }

    public void moveEnemies() {
        for (Enemy enemy : arena.getEnemies()) {
            if (!canFollowHero(enemy, arena.getHero()))
                continue;

            Position enemyPos = enemy.getPosition();
            Position heroPos = arena.getHero().getPosition();

            if (enemyPos.getX() < heroPos.getX()) {
                moveEnemy(enemy, enemyPos.getRight());
                enemy.setOrientation(Element.Orientation.RIGHT);
            }
            else if (enemyPos.getX() > heroPos.getX()) {
                moveEnemy(enemy, enemyPos.getLeft());
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
        if(position.getX() < 0 || position.getX() + enemy.getDimensions().getWidth() >= GameViewerConstants.SCREEN_WIDTH) return;
        if ( (!arena.collides(position, enemy.getDimensions())) && (!hasEnemy(enemy, position)) ) {
            enemy.setPosition(position);
            // TODO: USE COLLISIONS
            if (arena.getHero().getPosition().equals(position))
                arena.getHero().setHealth(arena.getHero().getHealth() - enemy.getStats().getPower());
        }
    }

    private boolean hasEnemy(Enemy enemy, Position position) {
        for (Enemy currEnemy : arena.getEnemies()) {
            if (currEnemy.equals(enemy)) continue;
            if (Position.checkElementsCollision(position, enemy.getDimensions(), currEnemy.getPosition(), currEnemy.getDimensions()))
                return true;
        }
        return false;
    }

    private boolean canFollowHero(Enemy enemy, Hero hero) {
        return Math.abs(enemy.getPosition().getX() - hero.getPosition().getX()) <= enemy.getStats().getViewDistance()
                && Math.abs(enemy.getPosition().getY() - hero.getPosition().getY()) <= hero.getDimensions().getHeight();
    }
}

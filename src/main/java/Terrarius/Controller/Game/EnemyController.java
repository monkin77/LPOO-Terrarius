package Terrarius.Controller.Game;

import static Terrarius.Viewer.Game.GameViewerConstants.*;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.enemies.Enemy;

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
        if (position.getX() < 0
                || position.getX() + enemy.getDimensions().getWidth() >= SCREEN_WIDTH
                || position.getY() < 0
                || position.getY() + enemy.getDimensions().getHeight() > SCREEN_HEIGHT - TOOLBAR_HEIGHT - 3)
            return; // TODO Refactor so we have constants somewhere common

        if ( (!arena.collidesWithBlocks(position, enemy.getDimensions())) && (!hasEnemy(enemy, position)) ) {
            enemy.setPosition(position);
        }
    }

    public void damageHero(){
        for (Enemy enemy : this.arena.getEnemies()){
            if (Position.checkElementsCollision(enemy.getPosition(), enemy.getDimensions(),
                    this.arena.getHero().getPosition(), this.arena.getHero().getDimensions()))
                arena.getHero().setHealth(arena.getHero().getStats().getHp() - enemy.getStats().getPower());
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
                && Math.abs(enemy.getPosition().getY() - hero.getPosition().getY()) <=
                    Math.max(hero.getDimensions().getHeight(), enemy.getDimensions().getHeight());
    }
}

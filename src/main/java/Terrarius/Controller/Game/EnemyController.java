package Terrarius.Controller.Game;

import static Terrarius.Viewer.Game.GameViewerConstants.*;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.enemies.Enemy;

import java.util.HashMap;
import java.util.Map;

public class EnemyController {
    private final Arena arena;
    private Map<Enemy, Integer> fallingVelocities;
    private Map<Enemy, Integer> gravityFrameCounters;

    public EnemyController(Arena arena) {
        this.arena = arena;
        this.updateEnemies();
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

    public void fallEnemies() {
        for (Enemy enemy : arena.getEnemies()) {
            Position lastPos = enemy.getPosition();
            for (int i = 0; i < fallingVelocities.get(enemy); ++i)
                moveEnemy(enemy, enemy.getPosition().getDown());

            if (lastPos.equals(enemy.getPosition())) {
                fallingVelocities.replace(enemy, 1);
                gravityFrameCounters.replace(enemy, 0);
                continue;
            }

            // TODO PUT GRAVITY IN GAME CONSTANTS ASWELL
            gravityFrameCounters.replace(enemy, gravityFrameCounters.get(enemy) + 1);
            if (gravityFrameCounters.get(enemy) % 10 == 0)
                fallingVelocities.replace(enemy, fallingVelocities.get(enemy) + 1);
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

    public void updateEnemies() {
        fallingVelocities = new HashMap<>();
        gravityFrameCounters = new HashMap<>();

        for (Enemy enemy : arena.getEnemies()) {
            fallingVelocities.put(enemy, 1);
            gravityFrameCounters.put(enemy, 0);
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

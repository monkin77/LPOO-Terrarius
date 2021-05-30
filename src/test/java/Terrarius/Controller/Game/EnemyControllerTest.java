package Terrarius.Controller.Game;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Model.Game.arena.Arena;
import Terrarius.Model.Game.elements.Element;
import Terrarius.Model.Game.elements.hero.Hero;
import Terrarius.Model.Game.elements.enemies.Enemy;
import Terrarius.Model.Game.elements.enemies.EnemyStats;
import Terrarius.Model.Game.elements.hero.HeroStats;
import Terrarius.Utils.Dimensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static Terrarius.Utils.GameConstants.*;

public class EnemyControllerTest {
    private EnemyController enemyController;
    private Arena arena;
    private Hero hero;
    private List<Enemy> enemies;

    @BeforeEach
    public void setup() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPosition()).thenReturn(new Position(6, 5));
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(1, 1));
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(5, 0), 100, 5, 1, 10));

        enemies = new ArrayList<>();

        enemies.add(Mockito.mock(Enemy.class));
        enemies.add(Mockito.mock(Enemy.class));
        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(5, 5));
        Mockito.when(enemies.get(1).getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(enemies.get(0).getStats()).thenReturn(new EnemyStats(100, 5, 5, new Level(2, 0)));
        Mockito.when(enemies.get(1).getStats()).thenReturn(new EnemyStats(100, 5, 6, new Level(2, 0)));
        Mockito.when(enemies.get(0).getDimensions()).thenReturn(new Dimensions(3, 3));
        Mockito.when(enemies.get(1).getDimensions()).thenReturn(new Dimensions(5, 5));

        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(arena.getEnemies()).thenReturn(enemies);

        enemyController = new EnemyController(arena);
    }

    @Test
    public void moveEnemies() {
        Mockito.when(enemies.get(1).getStats()).thenReturn(new EnemyStats(100, 5, 5, new Level(2, 0)));

        enemyController.moveEnemies();

        Mockito.verify(enemies.get(0), Mockito.times(1)).setPosition(new Position(6, 5));
        Mockito.verify(enemies.get(0), Mockito.times(1)).setOrientation(Element.Orientation.RIGHT);

        Mockito.verify(enemies.get(1), Mockito.times(1)).setPosition(new Position(9, 10));
        Mockito.verify(enemies.get(1), Mockito.times(1)).setOrientation(Element.Orientation.LEFT);

        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(0, 0));
        enemyController.moveEnemies();
        Mockito.verify(enemies.get(0), Mockito.times(1)).setPosition(Mockito.any());
        Mockito.verify(enemies.get(0), Mockito.times(1)).setOrientation(Element.Orientation.RIGHT);
    }

    @Test
    public void fallEnemies() {
        enemyController.fallEnemies();

        Mockito.verify(enemies.get(0), Mockito.times(1)).setPosition(new Position(5, 6));
        Mockito.verify(enemies.get(1), Mockito.times(1)).setPosition(new Position(10, 11));
    }

    @Test
    public void collidedWhenFalling() {
        Mockito.when(arena.collidesWithBlocks(Mockito.any(), Mockito.any())).thenReturn(true);
        enemyController.fallEnemies();

        for (Enemy enemy : enemies)
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
    }

    @Test
    public void screenLimits() {
        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(5, SCREEN_HEIGHT - TOOLBAR_HEIGHT - 3));
        enemyController.moveEnemies();

        Mockito.when(hero.getPosition()).thenReturn(new Position(SCREEN_WIDTH + 5, 5));
        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(SCREEN_WIDTH - 1, 5));

        Mockito.when(hero.getPosition()).thenReturn(new Position(-1, 5));
        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(0, 5));

        enemyController.moveEnemies();
        Mockito.verify(enemies.get(0), Mockito.never()).setPosition(Mockito.any());
    }

    @Test
    public void enemiesColliding() {
        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(8, 8));
        enemyController.moveEnemies();
        Mockito.verify(enemies.get(1), Mockito.never()).setPosition(Mockito.any());
        Mockito.verify(enemies.get(0), Mockito.times(1)).setPosition(new Position(7, 8));
    }

    @Test
    public void damageHero() {
        enemyController.damageHero();
        Mockito.verify(hero, Mockito.times(1)).setHealth(95);
    }
}

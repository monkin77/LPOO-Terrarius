package Terrarius.Controller.Game;

import Terrarius.Model.Dimensions;
import Terrarius.Model.Level;
import Terrarius.Model.Position;
import Terrarius.Model.arena.Arena;
import Terrarius.Model.elements.Element;
import Terrarius.Model.elements.Hero;
import Terrarius.Model.elements.enemies.Enemy;
import Terrarius.Model.elements.enemies.EnemyStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EnemyControllerTest {
    private EnemyController enemyController;
    private Arena arena;
    private Hero hero;
    private List<Enemy> enemies;

    @BeforeEach
    public void setup() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(hero.getPosition()).thenReturn(new Position(6, 5));
        Mockito.when(hero.getDimensions()).thenReturn(new Dimensions(1, 1));
        Mockito.when(hero.getHealth()).thenReturn(100);

        enemies = new ArrayList<>();

        enemies.add(Mockito.mock(Enemy.class));
        enemies.add(Mockito.mock(Enemy.class));
        Mockito.when(enemies.get(0).getPosition()).thenReturn(new Position(5, 5));
        Mockito.when(enemies.get(1).getPosition()).thenReturn(new Position(10, 10));
        Mockito.when(enemies.get(0).getStats()).thenReturn(new EnemyStats(100, 5, 5, new Level(2, 0)));
        Mockito.when(enemies.get(1).getStats()).thenReturn(new EnemyStats(100, 5, 0, new Level(2, 0)));

        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getHero()).thenReturn(hero);
        Mockito.when(arena.getEnemies()).thenReturn(enemies);
        Mockito.when(arena.collides(Mockito.any(), Mockito.any())).thenReturn(false);

        enemyController = new EnemyController(arena);
    }

    @Test
    public void moveEnemies() {
        Mockito.when(enemies.get(1).getPosition()).thenReturn(new Position(8, 5));
        Mockito.when(enemies.get(1).getStats()).thenReturn(new EnemyStats(100, 5, 5, new Level(2, 0)));

        enemyController.moveEnemies();

        Mockito.verify(enemies.get(0), Mockito.times(1)).setPosition(new Position(6, 5));
        Mockito.verify(enemies.get(0), Mockito.times(1)).setOrientation(Element.Orientation.RIGHT);

        Mockito.verify(enemies.get(1), Mockito.times(1)).setPosition(new Position(7, 5));
        Mockito.verify(enemies.get(1), Mockito.times(1)).setOrientation(Element.Orientation.LEFT);
    }

    @Test
    public void collidedWithHero() {
        enemyController.moveEnemies();

        Mockito.verify(hero, Mockito.times(1)).setHealth(95);
    }

    @Test
    public void fallEnemies() {
        enemyController.fallEnemies();

        Mockito.verify(enemies.get(0), Mockito.times(1)).setPosition(new Position(5, 6));
        Mockito.verify(enemies.get(1), Mockito.times(1)).setPosition(new Position(10, 11));
    }

    @Test
    public void collidedWhenFalling() {
        Mockito.when(arena.collides(Mockito.any(), Mockito.any())).thenReturn(true);
        enemyController.fallEnemies();

        for (Enemy enemy : enemies)
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
    }
}

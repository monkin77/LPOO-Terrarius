package Terrarius.Model.Game.elements.enemies;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import Terrarius.Utils.Dimensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class EnemyTest {
    Enemy enemy;
    Level level;

    @BeforeEach
    public void createLevel() {
        level = Mockito.mock(Level.class);
        Mockito.when(level.getNumLevel()).thenReturn(6);
        Mockito.when(level.getCurrentXP()).thenReturn((long)10);
        Mockito.when(level.getMaxXP()).thenReturn((long)100);
    }

    @Test
    public void enemyAndStats() throws FileNotFoundException, URISyntaxException {

        enemy = new Enemy(new Position(0, 0), level, "Zombie");

        Enemy enemy2 =Mockito.mock(Enemy.class);
        EnemyStats enemyStats = Mockito.mock(EnemyStats.class);

        Mockito.when(enemyStats.getHp()).thenReturn(20 + 6 * 1);
        Mockito.when(enemyStats.getPower()).thenReturn(10 + 6 * 1);
        Mockito.when(enemyStats.getViewDistance()).thenReturn(20);
        Mockito.when(enemyStats.getLevel()).thenReturn(level);

        Mockito.when(enemy2.getStats()).thenReturn(enemyStats);
        Mockito.when(enemy2.getComponentName()).thenReturn("Zombie");
        Mockito.when(enemy2.getDimensions()).thenReturn(new Dimensions(8, 4));
        Mockito.when(enemy2.getPosition()).thenReturn(new Position(0, 0));

        Assertions.assertTrue(enemy.equals(enemy2));
    }
}

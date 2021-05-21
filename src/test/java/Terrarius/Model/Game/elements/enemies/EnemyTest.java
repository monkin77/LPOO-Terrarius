package Terrarius.Model.Game.elements.enemies;

import Terrarius.Model.Game.Level;
import Terrarius.Model.Game.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnemyTest {
    Enemy enemy;
    Level level;

    @BeforeEach
    public void createLevel() {
        level = Mockito.mock(Level.class);
        Mockito.when(level.getNumLevel()).thenReturn(6);
    }

    @Test
    public void zombie() {
        enemy = new Zombie(new Position(10, 10), level);

        Assertions.assertEquals(16, enemy.getStats().getHp());
        Assertions.assertEquals(3, enemy.getStats().getPower());
        Assertions.assertEquals(6, enemy.getStats().getLevel().getNumLevel());
    }
}

package Terrarius.Model.Game;

import Terrarius.Model.Game.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelTest {
    Level level;

    @BeforeEach
    public void createLevel() {
        level = new Level(3, 0);
    }

    @Test
    public void increaseXP() {
        level.increaseXP(150);
        Assertions.assertEquals(150, level.getCurrentXP());
        Assertions.assertEquals(3 * Level.getXpMultiplier(), level.getMaxXP());
    }

    @Test
    public void equalsWithNewLevels() {
        Assertions.assertTrue(level.equals(level));

        Assertions.assertTrue(level.equals(new Level(3, 0)));
        Assertions.assertFalse(level.equals(new Level(4, 0)));
        Assertions.assertFalse(level.equals(new Level(3, 200)));
    }

    @Test
    public void equalsWithChanges() {
        level.increaseXP(500);
        Level newLevel = new Level(3, 500);

        Assertions.assertTrue(level.equals(newLevel));

        newLevel.increaseXP(200);

        Assertions.assertFalse(level.equals(newLevel));
    }
}

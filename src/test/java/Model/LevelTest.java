package Model;

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
    public void construction() {
        Assertions.assertEquals(3, level.getNumLevel());
        Assertions.assertEquals(0, level.getCurrentXP());
        Assertions.assertEquals(3 * Level.XP_MULTIPLIER, level.getMaxXP());
    }

    @Test
    public void increaseXP() {
        level.increaseXP(150);
        Assertions.assertEquals(150, level.getCurrentXP());
        Assertions.assertEquals(3 * Level.XP_MULTIPLIER, level.getMaxXP());
    }
}

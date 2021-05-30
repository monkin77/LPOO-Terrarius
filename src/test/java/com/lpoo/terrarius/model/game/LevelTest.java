package com.lpoo.terrarius.model.game;

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

    @Test
    public void XPDrop() {
        Assertions.assertEquals(6000, level.calcXpDrop());

        level = new Level(0, 3);
        Assertions.assertEquals(3, level.calcXpDrop());

        level = new Level(1, 50);
        Assertions.assertEquals(1050, level.calcXpDrop());

        level = new Level(0, 0);
        Assertions.assertEquals(0, level.calcXpDrop());
    }

    @Test
    public void levelUp() {
        level.increaseXP(3000);
        Assertions.assertEquals(4, level.getNumLevel());
        Assertions.assertEquals(0, level.getCurrentXP());

        level.increaseXP(4050);
        Assertions.assertEquals(5, level.getNumLevel());
        Assertions.assertEquals(50, level.getCurrentXP());
    }
}

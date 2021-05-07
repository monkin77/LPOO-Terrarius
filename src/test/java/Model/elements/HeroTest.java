package Model.elements;

import Model.Level;
import Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeroTest {
    Hero hero;

    @BeforeEach
    public void createHero() {
        hero = new Hero(new Position(10, 10));
    }

    @Test
    public void startLevel() {
        Level heroLevel = hero.getLevel();
        Assertions.assertEquals(heroLevel.getNumLevel(), 1);
        Assertions.assertEquals(heroLevel.getCurrentXP(), 0);
    }

    @Test
    public void startHealth() {
        Assertions.assertEquals(100, hero.getHealth());
    }
}

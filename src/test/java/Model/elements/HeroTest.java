package Model.elements;

import Model.Level;
import Model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HeroTest {
    Hero hero;

    @BeforeEach
    public void createHero() {
        hero = new Hero(new Position(10, 10));
    }

    @Test
    public void level() {
        Level heroLevel = hero.getLevel();
        Assertions.assertEquals(heroLevel.getLevel(), 1);
        Assertions.assertEquals(heroLevel.getCurrentXP(), 0);

        Level newLevel = Mockito.mock(Level.class);
        Mockito.when(newLevel.getLevel()).thenReturn(3);
        Mockito.when(newLevel.getCurrentXP()).thenReturn(250L);

        hero.setLevel(newLevel);
        heroLevel = hero.getLevel();
        Assertions.assertEquals(heroLevel.getLevel(), 3);
        Assertions.assertEquals(heroLevel.getCurrentXP(), 250L);
    }

    @Test
    public void health() {
        Assertions.assertEquals(100, hero.getHealth());

        hero.setHealth(90);
        Assertions.assertEquals(90, hero.getHealth());
    }
}

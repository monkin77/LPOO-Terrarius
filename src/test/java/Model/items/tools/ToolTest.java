package Model.items.tools;

import Model.Level;
import Model.elements.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ToolTest {
    Tool tool;
    Hero hero;

    @BeforeEach
    public void createHero() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getLevel()).thenReturn(new Level(16, 0));
    }

    @Test
    public void axe() {
        tool = new Axe(hero);
        Assertions.assertEquals(4, tool.getStats().getFightingPower());
        Assertions.assertEquals(13, tool.getStats().getMiningPower());
        Assertions.assertEquals(2, tool.getStats().getMiningHardness());
    }

    @Test
    public void pickaxe() {
        tool = new Pickaxe(hero);
        Assertions.assertEquals(2, tool.getStats().getFightingPower());
        Assertions.assertEquals(11, tool.getStats().getMiningPower());
        Assertions.assertEquals(3, tool.getStats().getMiningHardness());
    }

    @Test
    public void shovel() {
        tool = new Shovel(hero);
        Assertions.assertEquals(1, tool.getStats().getFightingPower());
        Assertions.assertEquals(10, tool.getStats().getMiningPower());
        Assertions.assertEquals(1, tool.getStats().getMiningHardness());
    }

    @Test
    public void sword() {
        tool = new Sword(hero);
        Assertions.assertEquals(9, tool.getStats().getFightingPower());
        Assertions.assertEquals(0, tool.getStats().getMiningPower());
        Assertions.assertEquals(0, tool.getStats().getMiningHardness());
    }
}

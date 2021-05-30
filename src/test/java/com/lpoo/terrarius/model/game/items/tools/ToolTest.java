package com.lpoo.terrarius.model.game.items.tools;

import com.lpoo.terrarius.model.game.Level;
import com.lpoo.terrarius.model.game.elements.hero.Hero;
import com.lpoo.terrarius.model.game.elements.hero.HeroStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ToolTest {
    Tool tool;
    Hero hero;

    @BeforeEach
    public void createHero() {
        hero = Mockito.mock(Hero.class);
        Mockito.when(hero.getStats()).thenReturn(new HeroStats(new Level(16, 0), 100, 5, 1, 10));
    }

    @Test
    public void pickaxe() throws FileNotFoundException, URISyntaxException {
        tool = new Tool(hero, "Pickaxe");
        Assertions.assertEquals(2, tool.getStats().getFightingPower());
        Assertions.assertEquals(11, tool.getStats().getMiningPower());
        Assertions.assertEquals(3, tool.getStats().getMiningHardness());
    }

    @Test
    public void sword() throws FileNotFoundException, URISyntaxException {
        tool = new Tool(hero, "Sword");
        Assertions.assertEquals(9, tool.getStats().getFightingPower());
        Assertions.assertEquals(0, tool.getStats().getMiningPower());
        Assertions.assertEquals(0, tool.getStats().getMiningHardness());
    }
}
